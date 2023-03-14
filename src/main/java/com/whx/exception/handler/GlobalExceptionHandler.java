package com.whx.exception.handler;

import com.whx.exception.GlobalException;
import com.whx.utils.RespBean;
import com.whx.utils.RespBeanEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e){
        if (e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.MOBILE_ERROR);
            respBean.setMessage("参数校验异常："+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }else if(e instanceof AccessDeniedException){
            return RespBean.error(RespBeanEnum.AUTHORITY_ERROR);
        }
        System.out.println(e);
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
