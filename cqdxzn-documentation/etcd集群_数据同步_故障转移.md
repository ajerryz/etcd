# etcd 集群
etcd 基于 Raft 共识算法实现集群选举和一致性，其中定义了三种核心角色：Leader（领导者）、Follower（跟随者） 和 Candidate（候选人）。这三种角色在不同阶段相互转换，确保集群的高可用和数据一致性。


# 三种角色
1. Leader(领导者)
- 职责：
  - 1.处理`所有客户端`写请求
  - 2.向`Follower`复制日志条目
  - 3.负责心跳`Headerbeat`广播，维持集群状态
- 特点:
  - 每个Raft集群在同一时刻只有一个Leader
  - Leader失效会触发新一轮选举
2.Follower(跟随者)
- 职责:
  - 1.响应Leader的日志复制请求
  - 2.响应Candidate的投票请求
  - 3.被动接受Leader的心跳信息
- 特点:
  - 完全被动，不主动发起请求
  - 如果长时间未收到Leader心跳，会转换为Candidate发起选举
3.Candidate(候选者)
- 职责：
  - 1.发起选举(RequestVote RPC)
  - 2.收集其他节点的投票
  - 3.如果获得多数票(N/2 + 1),则称为新Leader
- 特点：
  - 选举成功后称为Leader,失败则转回Follower
  - 多个Candidate可能导致选举失败，需要通过随机超时机制解决

