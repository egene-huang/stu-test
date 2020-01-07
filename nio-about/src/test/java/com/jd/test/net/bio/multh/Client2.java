package com.jd.test.net.bio.multh;

import java.net.Socket;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/24 15:56
 */
public class Client2 {

    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("localhost", Server.port);
        socket.getOutputStream().write("hello , i'm ".concat(Client2.class.getSimpleName()).getBytes(Server.utf8));
        socket.close();
    }
}
