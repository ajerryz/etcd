#!/usr/bin/env bash



# grpc-proxy启动
./etcd grpc-proxy start \
  --endpoints=http://localhost:2379,http://localhost:12379,http://localhost:22379 \
  --listen-addr=localhost:8989


# 使用场景
#   1.减轻集群负载：当 etcd 集群面临大量客户端请求，尤其是有许多重复的 watch 和 lease 请求时，gRPC proxy
#     可以通过合并请求来减轻集群的负载，提高整体性能。
#
#   2.多应用共享集群：在多个应用程序共享同一个 etcd 集群，且需要对键空间进行隔离和管理的情况下，gRPC proxy 的
#     命名空间功能可以确保各个应用程序之间的数据互不干扰。
#
#   3.提高客户端高可用性：通过客户端端点同步功能，gRPC proxy 可以为客户端提供自动发现代理端点的能力，使得客户端
#     在代理节点发生变化时能够自动切换，提高客户端的高可用性。