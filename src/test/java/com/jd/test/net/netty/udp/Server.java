package com.jd.test.net.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.nio.charset.Charset;

/**
 *  UDP
 * based netty echo server
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/25 16:47
 */
public class Server {
    static int port = 9998;
    static Charset utf8 = Charset.forName("utf-8");
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ServerChannelInitializer());
            //监听
            bootstrap.bind(port).sync().channel().closeFuture().await();
        }finally {
            group.shutdownGracefully();
        }
    }
}
