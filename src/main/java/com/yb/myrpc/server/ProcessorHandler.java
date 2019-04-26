package com.yb.myrpc.server;

import com.yb.myrpc.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author yebin
 * @version 1.0
 * @className ProcessorHandler
 * @description 任务处理请求
 * @date 2019/4/25 14:06
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    ProcessorHandler(Socket socket, Object service) {
        this.service = service;
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
           Object object=invoke(rpcRequest);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(objectInputStream !=null){
                try {
                    objectInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private Object invoke(RpcRequest rpcRequest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("服务端开始调用");
        Object[] parameters= rpcRequest.getParameters();
        Class[] parameterTypes = new Class[parameters.length];
        for (int i=0,length= parameters.length;i<length;i++){
            parameterTypes[i]=parameters[i].getClass();
        }
        Method method=service.getClass().getMethod(rpcRequest.getMethodName(),parameterTypes);
        return method.invoke(service,parameters);
    }
}
