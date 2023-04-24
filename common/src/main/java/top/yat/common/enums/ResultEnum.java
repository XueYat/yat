package top.yat.common.enums;

/**
 * @author XueYat
 * @date 2023/04/24
 */
public enum ResultEnum {

    // 这里是可以自己定义的，方便与前端交互即可
    SUCCESS(200, "成功"),
    SYSTEM_ERROR(-1, "系统错误"),
    USER_NOT_EXIST(1, "用户不存在"),
    USER_IS_EXISTS(2, "用户已存在"),
    DATA_IS_NULL(3, "数据为空"),
    ;

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
