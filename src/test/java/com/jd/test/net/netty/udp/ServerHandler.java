package com.jd.test.net.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/6 14:35
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        DatagramPacket datagramPacket = msg;
        ByteBuf data = datagramPacket.copy().content();
        byte[] bytes = new byte[data.readableBytes()];
        //将消息读入bytes中
        data.readBytes(bytes);
        System.out.println("收到消息: " + new String(bytes, Server.utf8));
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("hello, 你的消息我收到了", Server.utf8), datagramPacket.sender())).sync();
    }
}
