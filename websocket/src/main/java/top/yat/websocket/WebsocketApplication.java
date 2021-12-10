package top.yat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.yat.websocket.BulletChat.BulletChatServer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebsocketApplication {

    @Autowired
    private BulletChatServer bulletChatServer;

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

    @PostConstruct
    public void init(){
        bulletChatServer.BulletChatServer();
    }

}
