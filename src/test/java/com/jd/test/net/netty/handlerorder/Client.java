package com.jd.test.net.netty.handlerorder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 15:57
 */
public class Client {
    public static void main(String[] args)throws Exception {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
        try {
            bootstrap.connect("localhost", Server.port).sync().channel().closeFuture().sync();
        }finally {
            worker.shutdownGracefully();
        }
    }
}
