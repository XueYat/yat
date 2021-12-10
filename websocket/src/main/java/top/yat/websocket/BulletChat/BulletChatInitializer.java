package top.yat.websocket.BulletChat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

@Component
public class BulletChatInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 是netty针对http编解码的处理类，但是这些只能处理像http get的请求,也就是数据带在url问号后面的http请求
        pipeline.addLast(new HttpServerCodec());
        // 解决大文件传输过程中的内存溢出
        pipeline.addLast(new ChunkedWriteHandler());
        // 解析post请求的body参数信息
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 心跳机制主要是用来检测远端是否存活，如果不存活或活跃则对空闲Socket连接进行处理避免资源的浪费；
        pipeline.addLast(new IdleStateHandler(8, 10, 12));
        // 负责websocket握手以及控制帧（Close、Ping、Pong）的处理。文本和二进制的处理
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new BulletChatHandler());
    }
}