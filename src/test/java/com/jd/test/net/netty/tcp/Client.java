package com.jd.test.net.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/2 9:57
 */
public class Client {
    public static void main(String[] args)throws Exception {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
        ChannelFuture channelFuture = bootstrap.connect("localhost", Server.port).sync();
        try {
            channelFuture.channel().closeFuture().sync();
        }finally {
            clientGroup.shutdownGracefully();
        }
    }
}
