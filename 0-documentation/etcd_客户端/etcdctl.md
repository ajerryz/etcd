# etcdctl
help
```text
zhangning37@znMacbookPro bin % ./etcdctl --help
NAME:
  etcdctl - A simple command line client for etcd3.

USAGE:
  etcdctl [flags]

VERSION:
  3.6.0

API VERSION:
  3.6

COMMANDS:
  alarm disarm                  解除所有告警
  alarm list                    列出所有告警
  auth disable                  禁用认证功能
  auth enable                   启用认证功能
  auth status                   返回认证状态
  check datascale               检查给定服务器端点上不同工作负载的数据存储内存使用情况
  check perf                    检查 etcd 集群的性能
  compaction                    压缩 etcd 中的事件历史
  completion                    自动生成补全脚本
  defrag                        对指定端点的 etcd 成员存储进行进行碎片整理
  del                           删除指定的key或范围[key,range_end]
  downgrade cancel              取消集群正在进行的降级操作
  downgrade enable              启动集群降级操作
  downgrade validate            降级前验证集群降级能力
  elect                         观察并参与 leader 选举
  endpoint hashkv               打印 --endpoints 中每个端点的 KV 历史哈希
  endpoint health               检查 `--endpoints` 中指定端点的健康状态
  endpoint status               打印 `--endpoints` 中指定端点的状态信息
  get                           获取指定的键或键范围
  help                          查看任意命令的帮助信息
  lease grant                   创建租约
  lease keep-alive              保持租约活跃（续约）
  lease list                    列出所有活跃的租约
  lease revoke                  撤销租约
  lease timetolive              获取租约信息
  lock                          获取命名锁
  make-mirror                   在目标 etcd 集群创建镜像
  member add                    向集群添加成员
  member list                   列出集群所有成员
  member promote                将集群中的非投票成员提升为投票成员
  member remove                 从集群移除成员
  member update                 更新集群中的成员信息
  move-leader                   将 leadership 转移到另一个 etcd 集群成员
  put                           向存储中写入指定的键值对
  role add                      添加新角色
  role delete                   删除角色
  role get                      获取角色的详细信息
  role grant-permission         为角色授予键权限
  role list                     列出所有角色
  role revoke-permission        从角色撤销键权限
  snapshot save                 将 etcd 节点的后端快照存储到指定文件
  txn                           在一个事务中处理所有请求
  user add                      添加新用户
  user delete                   删除用户
  user get                      获取用户的详细信息
  user grant-role               为用户授予角色
  user list                     列出所有用户
  user passwd                   修改用户密码
  user revoke-role              从用户撤销角色
  version                       打印 etcdctl 的版本信息
  watch                         监听键或前缀的事件流

OPTIONS:
      --cacert=""                               使用此 CA 证书 bundle 验证启用 TLS 的安全服务器证书
      --cert=""                                 使用此 TLS 证书文件标识安全客户端
      --command-timeout=5s                      短运行命令的超时时间（不包括拨号超时）
      --debug[=false]                           启用客户端调试日志
      --dial-timeout=2s                         客户端连接的拨号超时时间
  -d, --discovery-srv=""                        用于查询描述集群端点的 SRV 记录的域名
      --discovery-srv-name=""                   使用 DNS 发现时要查询的服务名称
      --endpoints=[127.0.0.1:2379]              gRPC 端点列表
  -h, --help[=false]                            显示命令帮助信息
      --hex[=false]                             将字节字符串打印为十六进制编码字符串
      --insecure-discovery[=true]               接受描述集群端点的不安全 SRV 记录
      --insecure-skip-tls-verify[=false]        跳过服务器证书验证（警告：此选项仅应在测试环境启用）
      --insecure-transport[=true]               禁用客户端连接的传输层安全
      --keepalive-time=2s                       客户端连接的保活时间
      --keepalive-timeout=6s                    客户端连接的保活超时时间
      --key=""                                  使用此 TLS 密钥文件标识安全客户端
      --max-recv-bytes=0                        客户端响应接收的字节限制（若为 0，默认值为 "math.MaxInt32"）
      --max-request-bytes=0                     客户端请求发送的字节限制（若为 0，默认值为 2.0 MiB (2 * 1024 * 1024)）
      --password=""                             认证密码（若使用此选项，--user 选项不应包含密码）
      --user=""                                 用于认证的用户名[:密码]（若未提供密码则会提示输入）
  -w, --write-out="simple"                      设置输出格式（fields、json、protobuf、simple、table）
```