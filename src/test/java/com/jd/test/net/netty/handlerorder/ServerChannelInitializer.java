package com.jd.test.net.netty.handlerorder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/3 15:38
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ServerInboundHander1());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ServerInboundHander2());
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new ServerOutboundHandler1());
        ch.pipeline().addLast(new ServerOutboundHandler2());
    }
}
