# Etcd 选项配置
可以通过多种方式配置Etcd
- 命令行flag
- 环境变量: 每一个命令行flag对应一个环境变量。环境变量有前缀`ETCD_`。例如`--some-flag`将会是`ETCD_SOME_FALG`
- 配置文件: --config-file server.yml
```text
1.命令行优先级 > 环境变量
2.使用配置文件 将忽略命令行flag和环境变量。
```
[Etcd Command Flag 配置官方文档](https://etcd.io/docs/v3.6/op-guide/configuration/)