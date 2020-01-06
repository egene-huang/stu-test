package com.jd.test.net.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/2 9:45
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder());
//        ch.pipeline().addLast(new FixedLengthFrameDecoder(4096));
        ch.pipeline().addLast(new ServerChannelHandler());
        ch.pipeline().addLast(new StringEncoder());
    }
}
