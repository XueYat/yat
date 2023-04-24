package top.yat.common.utils;

import top.yat.common.base.Result;
import top.yat.common.enums.ResultEnum;

/**
 * @author XueYat
 * @date 2023/04/24
 */
public class ResultUtil {

    /**
     * 成功且带数据
     *
     * @param data 数据
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 成功但不带数据
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败，自定义错误
     *
     * @param code 失败code
     * @param msg  失败信息
     * @return Result
     */
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败，默认错误
     *
     * @return Result
     */
    public static <T> Result<T> error() {
        return error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg());
    }

}
