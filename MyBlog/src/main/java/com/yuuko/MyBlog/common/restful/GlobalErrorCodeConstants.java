package com.yuuko.MyBlog.common.restful;

/**
 * 错误码常量定义
 *
 * @author zhanghp
 * @date 2022-07-22 14:37
 */
public interface GlobalErrorCodeConstants {
    ErrorCode SUCCESS = new ErrorCode(0, "成功");
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "请求过于频繁，请稍后再试");
    ErrorCode NOTR_LOGIN = new ErrorCode(1000, "未登录");
    ErrorCode NOT_ROLE = new ErrorCode(1001, "无访问权限");
    ErrorCode EMIL_SEND_ERROR = new ErrorCode(1001, "无访问权限");

}
