package top.yat.websocket.core;


import lombok.Getter;

/**
 * websocket未登录第三方渠道
 */
@Getter
public enum WebSocketThirdChannelEnum {

    SAND_TABLE(-1, "yat", "三方用户");

    private final long uid;
    private final String code;
    private final String desc;

    WebSocketThirdChannelEnum(long uid, String code, String desc) {
        this.uid = uid;
        this.code = code;
        this.desc = desc;
    }

    public static WebSocketThirdChannelEnum valueOf(long uid) {
        for (WebSocketThirdChannelEnum enums : WebSocketThirdChannelEnum.values()) {
            if (enums.getUid() == uid) {
                return enums;
            }
        }
        return null;
    }

    public static WebSocketThirdChannelEnum valueOfCode(String code) {
        for (WebSocketThirdChannelEnum enums : WebSocketThirdChannelEnum.values()) {
            if (enums.getCode().equals(code)) {
                return enums;
            }
        }
        return null;
    }
}
