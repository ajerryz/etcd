#!/usr/bin/env bash

docker run -d \
  --name etcd0 \
  --restart always \
  -p 2379:2379 \
  -p 2380:2380 \
  -v /var/lib/etcd:/etcd-data \
  quay.io/coreos/etcd:v3.5.0 \
  /usr/local/bin/etcd \
  --name etcd0 \
  --data-dir /etcd-data \
  --listen-client-urls http://0.0.0.0:2379 \
  --advertise-client-urls http://192.168.1.101:2379 \
  --listen-peer-urls http://0.0.0.0:2380 \
  --initial-advertise-peer-urls http://192.168.1.101:2380 \
  --initial-cluster etcd0=http://192.168.1.101:2380 \
  --initial-cluster-token etcd-cluster-1 \
  --initial-cluster-state new