package com.jd.test.net.netty.handlerorder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 15:39
 */
public class ServerInboundHander1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String serverInboundHander1 = "ServerInboundHander1";
        System.out.println(serverInboundHander1 + "收到消息! => " + msg.toString());
//        ctx.fireChannelRead(Unpooled.copiedBuffer(serverInboundHander1 + ", " + msg.toString(), CharsetUtil.UTF_8));

        //是从当前节点开始找第一个outboundHandler
//        ctx.writeAndFlush(msg);
        //写-outxxx  pipeline是从tail开始
        ctx.pipeline().writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
