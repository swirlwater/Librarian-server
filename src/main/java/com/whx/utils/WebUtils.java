package com.whx.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string){
        try {
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
