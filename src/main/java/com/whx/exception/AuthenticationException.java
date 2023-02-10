package com.whx.exception;

import com.alibaba.fastjson.JSON;
import com.whx.utils.RespBean;
import com.whx.utils.RespBeanEnum;
import com.whx.utils.WebUtils;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权异常处理
 */
@Component
public class AuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) {
        RespBean respBean = RespBean.error(RespBeanEnum.AUTHENTICATION_ERROR);
        String json = JSON.toJSONString(respBean);
        //处理异常
        WebUtils.renderString(response,json);
    }
}
