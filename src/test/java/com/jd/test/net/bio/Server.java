package com.jd.test.net.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/18 19:58
 */
public class Server {
    static int port = 9999;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("localhost 开始监听端口 => " + port);
        byte[] bytes = new byte[1024];
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("监听到连接, 开始接收数据 ...");
            InputStream inputStream = socket.getInputStream();
            int read = inputStream.read(bytes);
            if (read != -1) {
                System.out.println("接收到数据 => " + new String(bytes));
            }
        }
    }
}
