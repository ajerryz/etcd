#!/usr/bin/env bash

CONFIG_FILE="$1.yml"

if [ ! -f "$CONFIG_FILE" ]; then
    echo "configFile:$CONFIG_FILE is not exist."
    exit 1
fi

./etcd --config-file "/etcd-cluster/$CONFIG_FILE"