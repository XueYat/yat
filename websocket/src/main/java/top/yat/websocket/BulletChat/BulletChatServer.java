package top.yat.websocket.BulletChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class BulletChatServer {

    public void BulletChatServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new BulletChatInitializer());
        ChannelFuture future = server.bind(9123);
    }

}
