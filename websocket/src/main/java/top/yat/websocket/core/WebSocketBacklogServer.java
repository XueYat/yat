package top.yat.websocket.core;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.yat.common.utils.DateUtils;
import top.yat.common.utils.StringUtils;
import top.yat.websocket.pojo.WebSocketVo;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;

@Slf4j
@Component
@ServerEndpoint(value = "/websocket", encoders = {ServerEncoder.class})
public class WebSocketBacklogServer {

    private static final String TOKEN = "token";

    private static final String CODE = "code";

    private WebSocketVo webSocketVo;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        String queryString = session.getQueryString();
        if (StringUtils.isEmpty(queryString)) {
            this.onClose();
            return;
        }
        String[] split = queryString.split("&");
        Long timestamp = DateUtils.getCurrentTimeStamp();
        Long userId = null;
        for (String str : split) {
            String[] keyValue = str.split("=");
            if (keyValue[0].equals(TOKEN)) {
                String token = keyValue[1];
                // 此处真实应为token校验，获取用户id，以下为模拟
                JSONObject user = new JSONObject();
                user.put("id", "1");
                user.put("name", "yat");
                JSONObject user2 = new JSONObject();
                user2.put("id", "2");
                user2.put("name", "yat");
                JSONObject userToken = new JSONObject();
                userToken.put("1", user);
                userToken.put("2", user2);
                JSONObject userByToken = userToken.getJSONObject(token);
                if (userByToken == null) {
                    this.onClose();
                    return;
                }
                userId = userByToken.getLong("id");
            }
            // 供第三方渠道调用
            if (keyValue[0].equals(CODE)) {
                String codeValue = keyValue[1];
                WebSocketThirdChannelEnum enums = WebSocketThirdChannelEnum.valueOfCode(codeValue);
                if (enums != null) {
                    userId = enums.getUid();
                }
            }
        }

        if (userId == null) {
            this.onClose();
            return;
        }

        webSocketVo = new WebSocketVo();
        webSocketVo.setUserId(userId);
        webSocketVo.setSession(session);
        webSocketVo.setTimestamp(timestamp);
        String key = getKey(userId, timestamp);

        // 加入set中
        WebSocketData.putWebSocketSet(key, webSocketVo);
        if (WebSocketData.getUserIdValueMap().containsKey(userId)) {
            WebSocketData.getUserIdValueMap().get(userId).add(key);
        } else {
            HashSet<String> set = new HashSet<>();
            set.add(key);
            WebSocketData.putUserIdValueMap(userId, set);
        }
        log.info("websocket连接成功，userId={},timestamp={}", webSocketVo.getUserId(), webSocketVo.getTimestamp());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketVo == null) {
            return;
        }
        log.info("websocket关闭连接，userId={},timestamp={}", webSocketVo.getUserId(), webSocketVo.getTimestamp());
        String key = getKey(webSocketVo.getUserId(), webSocketVo.getTimestamp());

        // 从set中删除
        WebSocketData.removeWebSocketSet(key);
        if (WebSocketData.getUserIdValueMap().containsKey(webSocketVo.getUserId())) {
            WebSocketData.getValueByUserId(webSocketVo.getUserId()).remove(key);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("收到来自窗口" + webSocketVo.getUserId() + "的关于" + webSocketVo.getTimestamp() + "信息:" + message);
        webSocketVo.getSession().getBasicRemote().sendObject(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误,错误原因为:{}", error.getMessage());
    }

    private static String getKey(Long userId, Long timestamp) {
        return userId + "-" + timestamp;
    }

}
