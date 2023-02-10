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
    LOGIN_ERROR(50010,"用户名或密码错误"),
    AUTHENTICATION_ERROR(401,"异常认证");
    private final Integer code;
    private final String message;
}
