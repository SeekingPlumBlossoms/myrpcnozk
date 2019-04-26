package com.yb.myrpc.client;

import java.lang.reflect.Proxy;

/**
 * @author yebin
 * @version 1.0
 * @className RpcClientProxy
 * @description TODO
 * @date 2019/4/25 17:28
 **/
@SuppressWarnings("unchecked")
public class RpcClientProxy {

    public <T> T clientProxy(final Class<T> interfaceClass,final String host,final int port){
      return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new RemoteInvocationHandler(port,host));
    }
}
