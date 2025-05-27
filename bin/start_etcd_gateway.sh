#!/usr/bin/env bash

# 以gateway模式启动
# 客户端直接连接gateway就行了
./etcd gateway start \
  --endpoints=http://localhost:2379,http://loclahost:12379,http://localhost:22379 \
  --listen-addr=localhost:7788