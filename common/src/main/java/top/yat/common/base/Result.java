package top.yat.common.base;

import lombok.Data;

/**
 * @author XueYat
 * @date 2023/04/24
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
    private String traceId;

    public Result() {
        super();
    }

    public Result(Integer code, String msg, T data, String traceId) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", traceId='" + traceId + '\'' +
                '}';
    }
}