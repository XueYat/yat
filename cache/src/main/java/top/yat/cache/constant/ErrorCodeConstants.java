package top.yat.cache.constant;

import top.yat.cache.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode PRODUCT_IS_NOT_EXISTS = new ErrorCode(1002000000, "商品不存在");
    ErrorCode AUTH_LOGIN_USER_DISABLED = new ErrorCode(1002000001, "登录失败，账号被禁用");
    ErrorCode AUTH_TOKEN_EXPIRED = new ErrorCode(1002000006, "Token 已经过期");
    ErrorCode AUTH_MOBILE_NOT_EXISTS = new ErrorCode(1002000007, "手机号不存在");

    ErrorCode ORDER_PARAM_ERROR = new ErrorCode(1002000008, "排序参数有误");

}
