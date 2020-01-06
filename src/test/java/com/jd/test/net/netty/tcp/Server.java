package com.jd.test.net.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/30 14:19
 */
public class Server {
    static int port = 8999;
    public static void main(String[] args)throws Exception {
        //接收请求 线程 并将accept请求分发出去
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();

        //处理i/o事件以及业务逻辑
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //服务端配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true)
//                .childOption(ChannelOption.SO_TIMEOUT, 1000)
                .childHandler(new ServerChannelInitializer());
        try {
            ChannelFuture channelFuture = bootstrap.bind(port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("服务绑定端口: " + port);
                } else {
                    System.out.println("端口绑定失败! 原因:" + future.cause().getMessage());
                }
            }).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }
}
