package com.jd.test.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 存在的问题: <br/>
 * 每次都会轮训全部连接，即使没有数据发送的连接， 所以在nio中如何获取这正需要轮训的连接交给了底层操作系统， linux的epoll 和windows的select
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/18 20:24
 */
public class Server {
    static ByteBuffer buffer = ByteBuffer.allocate(1024);
    static List<SocketChannel> channels = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 9090));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("监听到请求 ............");
                channels.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = channels.iterator();
            while (iterator.hasNext()) {
                buffer.clear();
                SocketChannel channel = iterator.next();
                channel.configureBlocking(false);
                int read = channel.read(buffer);
                if (read != -1) {
                    iterator.remove();
                    buffer.flip();
                    System.out.println(Charset.forName("utf-8").decode(buffer).toString());
                }
            }
        }
    }
}
