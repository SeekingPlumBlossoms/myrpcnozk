package com.yb.myrpc.client;

import com.yb.myrpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author yebin
 * @version 1.0
 * @className TcpTransport
 * @description socket传输
 * @date 2019/4/25 15:51
 **/
public class TcpTransport {
    int port;
    String host;

    TcpTransport(int port, String host) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() {
        System.out.println("准备创建socket链接,port=" + port + ",host=" + host);
        try {
            Socket socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("Socket连接创建失败！host：" + host + "，port：" + port);
        }
    }

    public  Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = newSocket();
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(rpcRequest);
                objectOutputStream.flush();

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object result = objectInputStream.readObject();

                objectInputStream.close();
                objectOutputStream.close();
                return result;
            } catch (IOException e) {
                throw new RuntimeException("发起远程调用异常！", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("发起远程调用异常！", e);
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
