package com.jd.test.net.netty.handlerorder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 15:39
 */
public class ServerInboundHander2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerInboundHander2, from client: " + msg.toString());
        ctx.channel().writeAndFlush(msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
