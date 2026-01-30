# jetcd

# jetcd的设计哲学
> 每个 etcd 能力 = 一个 Client；共享一个底层连接



顶层Client`io.etcd.jetcd.Client`,使用的唯一入口,Builder模式创建
- 管理了gRPC Channel
- 管理了生命周期(Close)
- 生产子Client


## 子Client的拆分设计
👉 和 etcd v3 API 一一对应

| etcd 能力     | jetcd Client        |
| ----------- | ------------------- |
| KV          | `KVClient`          |
| Watch       | `WatchClient`       |
| Lease       | `LeaseClient`       |
| Txn         | `KVClient.txn()`    |
| Cluster     | `ClusterClient`     |
| Maintenance | `MaintenanceClient` |
| Auth        | `AuthClient`        |
| Lock        | `LockClient`        |
| Election    | `ElectionClient`    |
