package com.jd.test.net.netty.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/25 17:11
 */
public class Client {
    static int port = 6685;
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(port);
        socket.connect(InetAddress.getByName("127.0.0.1"), Server.port);
        socket.setReuseAddress(true);
        //监听消息
        receive(socket);
        while (true) {
            //
            String msg = "hello, i'm Client";
            byte[] bytes = msg.getBytes(Server.utf8);
            try {
                socket.send(new DatagramPacket(bytes, bytes.length));
//                System.out.println("消息发送成功.");
            } catch (IOException e) {
                System.out.println("消息发送失败, " + e.getMessage());
                Thread.sleep(3000);
            }
        }
    }


    static void receive(DatagramSocket socket) {
        new Thread(() -> {
            while (true) {
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                if (socket == null || socket.isClosed()) {
                    continue;
                }

                try {
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    System.out.println("服务端响应: "+ msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
