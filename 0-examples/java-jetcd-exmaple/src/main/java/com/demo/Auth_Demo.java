package com.demo;

import java.util.concurrent.ExecutionException;

import com.demo.util.ClientFactory;

import io.etcd.jetcd.Auth;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.auth.AuthDisableResponse;
import io.etcd.jetcd.auth.AuthEnableResponse;
import io.etcd.jetcd.auth.AuthUserListResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Auth_Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = ClientFactory.getClient();

        Auth auth = client.getAuthClient();

        // 查询用户列表
        AuthUserListResponse authUserListResponse = auth.userList().get();
        log.info("userList:{}", authUserListResponse.getUsers());

        AuthEnableResponse authEnableResponse = auth.authEnable().get();

        AuthDisableResponse authDisableResponse = auth.authDisable().get();
        // 等等与 etcdctl auth 相对应的命令
    }
}
