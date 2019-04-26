package com.yb.myrpc.demo;

import com.yb.myrpc.client.RpcClientProxy;

/**
 * @author yebin
 * @version 1.0
 * @className ClientDemo
 * @description 客户端demo
 * @date 2019/4/25 17:32
 **/
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        IHelloService iHelloService = proxy.clientProxy(IHelloService.class,"127.0.0.1",12345);
        String name=iHelloService.sayHello("张三");
        System.out.println(name);
    }
}
