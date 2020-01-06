package com.jd.test.net.nio;

import java.io.IOException;
import java.net.Socket;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/18 20:30
 */
public class Client {
    public static void main(String[] args) throws IOException {
        {Socket socket = new Socket("localhost", 9090);
        socket.getOutputStream().write("hello, world, i'm No.1".getBytes());
        socket.close();}
        {Socket socket = new Socket("localhost", 9090);
        socket.getOutputStream().write("hello, world, i'm No.2".getBytes());
        socket.close();}
    }
}
