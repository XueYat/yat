package top.yat.websocket.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * socket 发送到redis的对象
 */
@Data
@NoArgsConstructor
public class WebSocketMessageVo {

    private List<Long> userIdList;

    private Object message;

    public WebSocketMessageVo(Object message) {
        this.message = message;
    }

    public WebSocketMessageVo(Long userId, Object message) {
        this.userIdList = Collections.singletonList(userId);
        this.message = message;
    }

    public WebSocketMessageVo(List<Long> userIdList, Object message) {
        this.userIdList = userIdList;
        this.message = message;
    }
}
