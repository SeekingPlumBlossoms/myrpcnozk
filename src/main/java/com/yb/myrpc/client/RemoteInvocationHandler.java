package com.yb.myrpc.client;

import com.yb.myrpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yebin
 * @version 1.0
 * @className RemoteInvocationHandler
 * @description 动态代理
 * @date 2019/4/25 17:24
 **/
public class RemoteInvocationHandler implements InvocationHandler {
    private int port;
    private String host;

    RemoteInvocationHandler(int port, String host) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        System.out.println(method.getDeclaringClass().getName());
        System.out.println(method.getClass().getName());
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        TcpTransport tcpTransport = new TcpTransport(port, host);
        return tcpTransport.send(rpcRequest);
    }
}
