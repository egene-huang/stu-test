package com.jd.test.net.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 发送消息给服务端，如果服务端连接丢失， 需要重新连接然后再次发送消息
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/24 17:32
 */
public class Client {

    /**
     * client name
     */
    private String clientName;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public void start() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        InetSocketAddress localhost = new InetSocketAddress("localhost", Server.port);
        SocketChannel clientChannel = SocketChannel.open(localhost);
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_WRITE);
        clabel:
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.print(".");
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isWritable()) {
                    byteBuffer.clear();
                    System.out.println("发送消息...");
                    byteBuffer.put(ByteBuffer.wrap(("hello, i'm " + this.clientName).getBytes(Server.utf8)));
                    byteBuffer.flip();
                    try {
                        int write = ((SocketChannel) key.channel()).write(byteBuffer);
                        //返回0 means 服务端没有读取完，缓冲区还有未读取数据
                        if (write == 0) {
//                        clientChannel.close();
                            System.out.println("服务端正在读取数据...");
                        }else {
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }catch (IOException e) {
//                        System.out.println("服务器连接异常: " + e.getMessage());
                        boolean connected = false;
                        System.out.println("重新连接中...");
                        while (!connected) {
                            try {
                                clientChannel.close();
                            } catch (IOException ex) {
                                System.out.println("关闭异常...");
                            }
                            try {
                                clientChannel = SocketChannel.open(localhost);
                                clientChannel.configureBlocking(false);
                                clientChannel.register(selector, SelectionKey.OP_WRITE);
                                connected = true;
                                System.out.println("重新连接服务器成功.");
                                continue clabel;
                            } catch (Exception ex) {
                                System.out.println("服务器连接失败..." + ex.getMessage());
//                                System.out.println("服务器连接失败...");
                            }
                        }
                    }

                }else if (key.isReadable()) {
                    SocketChannel serverChannel = (SocketChannel) key.channel();
                    try {
                        byteBuffer.clear();
                        int read = serverChannel.read(byteBuffer);
                        switch (read) {
                            case 0:
                                System.out.println("socketChannel#read == 0");
                                break;
                            case -1:
                                System.out.println("socketChannel#read == -1");
                                break;
                        }
                        if (read == 0 || read == -1) {
                            continue;
                        }

                        byteBuffer.flip();
                        System.out.println("[" + this.clientName + "]服务器响应: " + Server.utf8.decode(byteBuffer).toString());
                        //一个回声
//                    key.interestOps(SelectionKey.OP_WRITE);
                    }catch (IOException e) {
                        System.out.println("异常, 可能服务器已关闭. " + e.getMessage());
                        //重新注册写事件
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }
}
