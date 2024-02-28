package top.yat.websocket.service;

public interface WebSocketService {

    /**
     * 发送消息到所有客户端
     */
    void sendToAll(Object message);

    /**
     * 指定给某个连接发送信息
     */
    void sendToOne(Object message, Long userId);

}
