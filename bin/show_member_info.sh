#!/bin/bash

./etcdctl --endpoints=http://localhost:2379,http://localhost:12379,http://localhost:22379 endpoint status --write-out="table"
