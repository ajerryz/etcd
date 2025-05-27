#!/usr/bin/env bash


# 添加root账号,确认密码
./etcdctl user add root

# 添加root角色
./etcdctl role add root

# 授予root角色 全部权限
./etcdctl role grant-permission root readwrite /

# 将root角色 授予root账号
./etcdctl user grant-role root root

