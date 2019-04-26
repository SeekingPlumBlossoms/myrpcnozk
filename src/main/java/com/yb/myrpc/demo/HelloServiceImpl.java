package com.yb.myrpc.demo;

/**
 * @author yebin
 * @version 1.0
 * @className HelloServiceImpl
 * @description TODO
 * @date 2019/4/25 17:21
 **/
public class HelloServiceImpl implements IHelloService {
    public String sayHello(String name) {
        return "你好，" + name;
    }
}
