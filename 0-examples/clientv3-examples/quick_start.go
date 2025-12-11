package main

import (
	"context"
	"fmt"
	"time"

	"go.etcd.io/etcd/api/v3/mvccpb"
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

	// write
	putResp, err := client.Put(context.Background(), "api/v2/demo", "this is api v2 demo")
	if err != nil {
		panic(fmt.Errorf("Put 失败.%w", err))
	} else {
		preKv := putResp.PrevKv
		fmt.Printf("Put Success!\n")
		fmt.Printf("preKv:%v\n", preKv)
	}

	getResp, err := client.Get(context.Background(), "/api/v1/demo", clientv3.WithPrefix())
	if err != nil {
		panic(fmt.Errorf("GET 失败.%w", err))
	} else {
		fmt.Printf("Get Success!\n")
		more := getResp.More
		count := getResp.Count
		fmt.Printf("more:%v count:%v\n", more, count)
		for k, v := range getResp.Kvs {
			fmt.Printf("k:%v v:%v\n", k, v)
		}
	}

	watchChan := client.Watch(context.Background(), "api/v2/demo", clientv3.WithPrefix(), clientv3.WithPrevKV())
	for {
		fmt.Println("loop")
		select {
		case watchResp := <-watchChan:
			fmt.Println("watchResp:", watchResp)
			for _, event := range watchResp.Events {
				if mvccpb.DELETE == event.Type {
					fmt.Printf("===> recive [DELETE] event\n")
					fmt.Printf("k:%v v:%v preK:%v preV:%v\n", string(event.Kv.Key), string(event.Kv.Value), string(event.PrevKv.Key), string(event.PrevKv.Value))
				} else if mvccpb.PUT == event.Type {
					fmt.Printf("===> recive [PUT] event\n")
					fmt.Printf("%v\n", event.Kv.String())
					//fmt.Printf("k:%v v:%v preK:%v preV:%v\n", string(event.Kv.Key), string(event.Kv.Value), string(event.PrevKv.Key), string(event.PrevKv.Value))

				}
			}
		}
	}

}
