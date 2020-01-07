package com.jd.test.net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/18 20:06
 */
public class Client {

    static int port = 9999;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", port);
        Scanner scanner = new Scanner(System.in);
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            String next = scanner.next();
            outputStream.write(next.getBytes());
            outputStream.flush();
            if ("exit".equals(next)) {
                break;
            }
        }
        socket.close();
        scanner.close();
    }
}
