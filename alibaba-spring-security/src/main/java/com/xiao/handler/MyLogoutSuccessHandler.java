package com.xiao.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author xiao */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        // 设置响应状态码为200
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        // 重定向到 `login.html` 页面
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.html");
    }
}