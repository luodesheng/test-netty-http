package lds.test;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestHandler extends ChannelHandlerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(HttpRequestHandler.class);
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		if (msg instanceof DefaultHttpRequest) {
			//DefaultHttpRequest request = (DefaultHttpRequest) msg;
			LOG.info(msg.toString());
		} else if (msg instanceof LastHttpContent) {
		//	HttpContent httpContent = (LastHttpContent)msg;
			
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.wrappedBuffer("luodesheng".getBytes()));
			response.headers().add("Connection", "keep-alive");
			
			ctx.writeAndFlush(response);
			
			ctx.close();
		}
		
	}

}
