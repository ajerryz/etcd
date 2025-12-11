# Etcd Gateway
# 什么是 etcd gateway?
etcd 网关是一个简单的 TCP 代理，用于将网络数据转发到 etcd 集群。该网关无状态且透明；它既不检查客户端请求，也不干扰集群响应。它不会终止 TLS 连接，不会代表其客户端执行 TLS 握手，也不会验证连接是否安全。

该网关支持多个 etcd 服务器端点，并采用简单的轮询策略。它仅路由到可用的端点，并向客户端隐藏故障信息。未来可能会支持其他重试策略，例如加权轮询。



# 启动 etcd gateway
```shell
etcd gateway start --endpoints=infra0.example.com:2379,infra1.example.com:2379,infra2.example.com

# --endpoints 用于转发客户端连接的 etcd 服务器目标的逗号分隔列表。
#              默认：127.0.0.1:2379,必须包含端口。

# --discovery-srv 用于通过 SRV 记录引导集群端点的 DNS 域。
#

# --listen-addr 用于接受客户端请求的绑定接口和端口。
#                默认：127.0.0.1:23790
# --retry-delay 重试连接失败端点之前的延迟持续时间。
#               默认值：1m0s (格式中需要时间单位）


```