package com.yb.myrpc.demo;

import com.yb.myrpc.server.RpcServer;

/**
 * @author yebin
 * @version 1.0
 * @className ServerDemo
 * @description 服务端测试
 * @date 2019/4/25 17:21
 **/
public class ServerDemo {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(new HelloServiceImpl(),12345);
    }
}
