package top.yat.websocket.core;

import org.springframework.stereotype.Component;
import top.yat.websocket.pojo.WebSocketVo;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketData {
    // 存放每个客户端对应的MyWebSocket对象。
    private static final ConcurrentHashMap<String, WebSocketVo> WEB_SOCKET_SET = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, HashSet<String>> USER_ID_VALUE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, WebSocketVo> getWebSocketSet() {
        return WEB_SOCKET_SET;
    }

    public static WebSocketVo getWebSocketVo(String key) {
        return WEB_SOCKET_SET.get(key);
    }

    /**
     * 添加session对象
     */
    public static void putWebSocketSet(String key, WebSocketVo webSocketVo) {
        WebSocketData.WEB_SOCKET_SET.put(key, webSocketVo);
    }

    public static void removeWebSocketSet(String key) {
        WebSocketData.WEB_SOCKET_SET.remove(key);
    }

    public static ConcurrentHashMap<Long, HashSet<String>> getUserIdValueMap() {
        return USER_ID_VALUE_MAP;
    }

    public static HashSet<String> getValueByUserId(Long userId) {
        return USER_ID_VALUE_MAP.get(userId);
    }

    public static void putUserIdValueMap(Long userId, HashSet<String> set) {
        WebSocketData.USER_ID_VALUE_MAP.put(userId, set);
    }
}
