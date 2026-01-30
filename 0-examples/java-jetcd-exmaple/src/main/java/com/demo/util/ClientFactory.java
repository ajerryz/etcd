package com.demo.util;

import io.etcd.jetcd.Client;

public class ClientFactory {

    public static final String ENDPOINT = "http://localhost:2379";

    public static Client getClient() {
        return Client.builder().endpoints(ENDPOINT).build();
    }
}
