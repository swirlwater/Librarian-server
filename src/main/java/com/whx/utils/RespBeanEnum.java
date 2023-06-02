package com.whx.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务器端异常"),
    AUTHENTICATION_ERROR(401,"异常认证"),
    AUTHORITY_ERROR(403,"权限不足"),
    LOGIN_ERROR(50010,"用户名或密码错误"),
    MOBILE_ERROR(50011,"手机号码格式不正确"),
    CAPTCHA_ERROR(50012,"验证码错误"),
    CAPTCHA_OUT_ERROR(50012,"验证码过期"),
    USER_NOT_ERROR(50013,"用户不存在"),
    PASSWORD_ERROR(50014,"密码错误"),
    BOOK_ERROR(50020,"书籍不存在"),
    NUM_ERROR(50021,"库存不足"),
    DELETE_FILE_ERROR(50031,"删除文件错误");
    private final Integer code;
    private final String message;
}
