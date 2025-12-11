# Etcd grpc-proxy
在 gRPC 层运行的无状态 etcd 反向代理。[Etcd grpc-proxy 官方文档](https://etcd.io/docs/v3.6/op-guide/grpc_proxy/)


gRPC 代理是一个在 gRPC 层（L7）运行的`无状态` etcd 反向代理。该代理旨在减少核心 etcd 集群的总处理负载。为了实现水平扩展，它会`合并` watch 和 lease API 请求。为了保护集群免受恶意客户端的攻击，它会缓存键范围请求。

gRPC 代理支持多个 etcd 服务器端点。代理启动时，会随机选择一个 etcd 服务器端点使用。此端点负责处理所有请求，直到代理检测到端点故障。如果 gRPC 代理检测到端点故障，它会切换到其他可用端点，以向客户端隐藏故障信息。未来可能会支持其他重试策略，例如加权轮询。

