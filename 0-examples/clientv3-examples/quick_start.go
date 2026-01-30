package main

import (
	"context"
	"fmt"
	"time"

	clientv3 "go.etcd.io/etcd/client/v3"
)

func main() {

	client, err := clientv3.New(clientv3.Config{
		Endpoints:   []string{"localhost:2379"},
		DialTimeout: time.Second * time.Duration(5),
	})
	if err != nil {
		panic(fmt.Errorf("连接 etcd 失败。: %w", err))
	}
	defer client.Close()

	putResponse, err := client.Put(context.Background(), "foo", "bar", clientv3.WithLease(clientv3.LeaseID(10)))
	if err != nil {
		panic(err)
	}

}
