package top.yat.websocket.pojo;

import lombok.Data;

import javax.websocket.Session;

@Data
public class WebSocketVo {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private Long userId;

    /**
     * 连接时间
     */
    private Long timestamp;

}
