package com.demo;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import com.demo.util.ClientFactory;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.PutOption;

public class KV_Demo {

    private static final Client CLIENT = ClientFactory.getClient();

    public static final ByteSequence KEY = ByteSequence.from("/service/a-service", StandardCharsets.UTF_8);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KV kvClient = CLIENT.getKVClient();
        // KVClient: put get delete compact txn

        GetResponse getResponse = kvClient.get(KEY,
                GetOption.builder().withSerializable(true).build()).get();



    }
}
