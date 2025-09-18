// Copyright 2015 The etcd Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// main 包是 etcd 真正的入口点包（位于 go.etcd.io/etcd/etcdmain）的简单包装器，用于确保 etcd 仍然
// “可以通过 go get 获取”；例如，`go get go.etcd.io/etcd` 可以按预期工作，并且在 $GOBIN/etcd 中构建一个二进制文件
//
// 该包不应以任何方式扩展或修改；要修改 etcd 二进制文件，请在 `go.etcd.io/etcd/etcdmain` 包中工作。
package main

import (
	"os"

	"go.etcd.io/etcd/server/v3/etcdmain"
)

func main() {
	etcdmain.Main(os.Args)
}
