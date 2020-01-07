package com.jd.test.net.bio.multh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/24 15:50
 */
public class Server {

    static int port = 8888;

    static Charset utf8 = Charset.forName("utf-8");

    public static void main(String[] args)throws Exception {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务已启动, 监听端口: " + port);
            while (true) {
                Socket accept = serverSocket.accept();
                new Thread(() -> {
                    System.out.println("等待接收数据........");
                    try {
                        accept.getInputStream().read(buffer);
                        System.out.println("接收到数据: " + new String(buffer, utf8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            accept.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
