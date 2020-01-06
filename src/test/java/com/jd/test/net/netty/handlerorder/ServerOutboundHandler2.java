package com.jd.test.net.netty.handlerorder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 15:42
 */
public class ServerOutboundHandler2 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("ServerOutboundHandler2");
        ctx.writeAndFlush(Unpooled.copiedBuffer("ServerOutboundHandler2,  " + msg.toString(), CharsetUtil.UTF_8));
    }
}
