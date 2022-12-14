package me.zz.netty.socket.serverhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author qingzhi
 * @date 2022/11/11 17:58
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Netty releases it for you when it is written out to the wire.
        ctx.write(msg);
        ctx.flush();
    }
}
