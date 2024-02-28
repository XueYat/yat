package top.yat.websocket.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.yat.common.utils.StringUtils;
import top.yat.websocket.pojo.WebSocketMessageVo;
import top.yat.websocket.pojo.WebSocketVo;

import java.util.HashSet;
import java.util.List;

/**
 * redis消息订阅-业务处理
 */
@Slf4j
@Component
public class WebSocketListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("消息订阅成功，message: {}", message.toString());
        String messageStr = message.toString();
        if (StringUtils.isEmpty(messageStr)) {
            return;
        }

        WebSocketMessageVo vo = JSONObject.parseObject(JSON.parse(messageStr).toString(), WebSocketMessageVo.class);
        if (vo.getUserIdList() == null) {
            // 发送全部
            sendToAll(vo.getMessage());
        } else {
            sendToSomeOne(vo.getMessage(), vo.getUserIdList());
        }
    }

    private void sendToAll(Object message) {
        for (WebSocketVo value : WebSocketData.getWebSocketSet().values()) {
            try {
                if (value.getUserId() < 0) {
                    continue;
                }
                value.getSession().getBasicRemote().sendObject(message);
            } catch (Exception e) {
                log.error("连接异常,value={}", value);
            }
        }
    }

    private void sendToSomeOne(Object message, List<Long> userIdList) {
        for (Long userId : userIdList) {
            HashSet<String> set = WebSocketData.getValueByUserId(userId);
            if (CollectionUtils.isEmpty(set)) {
                continue;
            }
            for (String key : set) {
                try {
                    WebSocketData.getWebSocketVo(key).getSession().getBasicRemote().sendObject(message);
                } catch (Exception e) {
                    log.error("连接异常,key={}", key);
                }
            }
        }
    }

}
