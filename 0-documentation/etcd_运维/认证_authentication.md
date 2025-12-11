# Etcd Authentication
etcd 身份验证和基于角色的访问控制指南
- 基本身份认证和基于角色的访问控制


# 基于角色的访问控制(RBAC)
身份验证功能已于 etcd 2.1 版本中新增。etcd v3 API 对身份验证功能的 API 和用户界面进行了轻微修改，以更好地适应新的数据模型。

## 特殊的root账号和root角色
- 在使用RBAC之前，必须创建一个完全访问权限的root用户。创建root用户是为了管理role和普通user.
- `root`用户必须有`root`角色。`root`角色必须有权更改etcd中的任何内容。
- `root`角色还可授权给其他用户，该角色不仅可以全局读写访问权限，还拥有更新集群身份验证配置的权限，还有常规授予集群维护的权限，包括(修改成集群成员，对存储碎片整理，创建快照)


```shell
# 添加root账号,确认密码
./etcdctl user add root

# 添加root角色
./etcdctl role add root

# 授予root角色 全部权限
./etcdctl role grant-permission root readwrite /

# 将root角色 授予root账号
./etcdctl user grant-role root root
```
然后就可以起开RBAC的身份认证了
```shell
# 开启
./etcdctl auth enable
# 关闭
./etcdctl auth disable
# 查看认证状态
./etcdctl --user root:root auth status

# 后续使用时就需要认证
./etcdctl --user root:root put demoKye demoValue
./etcdctl --user root:root get demoKey
```