package main

import (
	"context"
	"fmt"
	clientv3 "go.etcd.io/etcd/client/v3"
	"time"
)

/*
etcd 客户端结构体

	type Client struct {
		Cluster
		KV
		Lease
		Watcher
		Auth
		Maintenance

		conn *grpc.ClientConn

		cfg      Config
		creds    grpccredentials.TransportCredentials
		resolver *resolver.EtcdManualResolver

		epMu      *sync.RWMutex
		endpoints []string

		ctx    context.Context
		cancel context.CancelFunc

		// Username is a user name for authentication.
		Username string
		// Password is a password for authentication.
		Password        string
		authTokenBundle credentials.PerRPCCredentialsBundle

		callOpts []grpc.CallOption

		lgMu *sync.RWMutex
		lg   *zap.Logger
	}
*/
func main() {
	client, err := clientv3.New(clientv3.Config{
		Endpoints:   []string{"http://127.0.0.1:2379"},
		DialTimeout: 5 * time.Second,
		Username:    "root",
		Password:    "root",
	})
	if err != nil {
		panic(fmt.Errorf("connect etcd failed: %w", err))
	}
	defer client.Close()

	putResponse, err := client.Put(context.Background(), "name", "cqdxzn")
	if err != nil {
		panic(fmt.Errorf("put failed: %w", err))
	}

	putResponse, _ = client.Put(context.Background(), "name", "cqdxzn2")
	fmt.Printf("preValue:%v\n", putResponse.PrevKv)

	getResponse, err := client.KV.Get(context.Background(), "name")
	if err != nil {
		panic(fmt.Errorf("get failed: %w", err))
	}

	for _, val := range getResponse.Kvs {
		key := val.Key
		createRevision := val.CreateRevision
		modRevision := val.ModRevision
		version := val.Version
		value := val.Value
		lease := val.Lease
		fmt.Printf("%v:%v  lease:%v\ncreateReviseion:%v\nmodRevision:%v\nversion:%v\n", string(key), string(value), lease, createRevision, modRevision, version)
	}

}
