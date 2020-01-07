package com.jd.test.net.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * echo服务， 将客户端发送数据原样响应给客户端
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/19 11:08
 */
public class Server {
    static int port = 9999;
    static Charset utf8 = Charset.forName("utf-8");
    static ByteBuffer buffer = ByteBuffer.allocate(1024);
    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress("localhost", port));
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动， 监听端口: " + port);
        String tips = null;
        while (true) {
            if (selector.select(1000) == 0) {
                if (tips == null || "".equals(tips)) {
                    tips = "等待连接";
                    System.out.print(tips);
                }
                System.out.print(".");
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("\n监听到连接: " + clientChannel);
                }else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    buffer.clear();
                    try {
                        if (!clientChannel.isConnected()) {
                            System.out.println("\n客户端连接已关闭....");
                            clientChannel.close();
                            continue;
                        }
                        int read = clientChannel.read(buffer);
                        //这里buffer不会空间不足，不用判断返回0
                        if (read == -1) {
                            //客户端发送数据完毕， 并且主动关闭了连接
                            try {
                                System.out.println("\n客户端: " + clientChannel.socket().getInetAddress().getHostName());
                            } catch (Exception e) {
                                System.out.println("\nxx异常: " + e.getMessage());
                            }
                            clientChannel.close();
                        }if (read == 0) {
                            //如果接收数据大于buffer 这里需要多次读取 while 使用appender拼接处理
                            System.out.println("\n没有数据或buffer空间不足");
                        }else {
//                            buffer.mark();
//                            System.out.println("\n连接: " + clientChannel);
//                            buffer.reset();
                            //继续监听读  然后返回客户端发送数据
                            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        }
                    }catch (IOException e) {
                        System.out.println("\n异常: " + e.getMessage());
                        key.cancel();
                        if (clientChannel != null) {
                            clientChannel.close();
                        }
                    }
                }else if (key.isWritable()) {
                    buffer.flip();
                    SocketChannel clientchChannel = (SocketChannel) key.channel();
                    clientchChannel.write(buffer);
                    if (!buffer.hasRemaining()) {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        }
    }
}
