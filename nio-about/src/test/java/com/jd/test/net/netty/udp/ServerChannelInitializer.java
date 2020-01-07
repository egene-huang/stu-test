package com.jd.test.net.netty.udp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * UDP
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/25 16:51
 */
public class ServerChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new ServerHandler());
    }
}
