package com.jd.test.net.netty.handlerorder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 16:02
 */
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ClientChannelHandler from server: " + msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        ctx.channel().writeAndFlush("hello, server!");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
//        ctx.writeAndFlush("hello, server!");
    }
}
