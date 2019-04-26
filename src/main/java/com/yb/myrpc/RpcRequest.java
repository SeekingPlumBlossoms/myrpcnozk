package com.yb.myrpc;

import java.io.Serializable;

/**
 * @author yebin
 * @version 1.0
 * @className RpcRequest
 * @description Rpc请求实体
 * @date 2019/4/25 14:10
 **/
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 4259426526642196325L;

    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public RpcRequest setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public RpcRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public RpcRequest setParameters(Object[] parameters) {
        this.parameters = parameters;
        return this;
    }
}
