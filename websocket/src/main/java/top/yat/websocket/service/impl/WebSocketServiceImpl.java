package top.yat.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.yat.common.constant.Constants;
import top.yat.websocket.core.RedisCache;
import top.yat.websocket.pojo.WebSocketMessageVo;
import top.yat.websocket.service.WebSocketService;

import javax.annotation.Resource;

/**
 * websocket业务实现类
 */
@Service
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    @Resource
    private RedisCache redisCache;

    @Override
    @Async
    public void sendToAll(Object message) {
        WebSocketMessageVo vo = new WebSocketMessageVo(message);
        redisCache.convertAndSend(Constants.RedisChannel.WEBSOCKET_CHANNEL, JSON.toJSONString(vo));
    }

    @Override
    @Async
    public void sendToOne(Object message, Long userId) {
        if (userId == null) {
            return;
        }
        WebSocketMessageVo vo = new WebSocketMessageVo(userId, message);
        redisCache.convertAndSend(Constants.RedisChannel.WEBSOCKET_CHANNEL, JSON.toJSONString(vo));
    }
}
