package com.faxd.effirecord.security.handler;

import com.faxd.effirecord.exception.MyAccessDeniedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        if (accessDeniedException instanceof MyAccessDeniedException) {
            response.getWriter().write("没有权限异常:" + accessDeniedException.getMessage());
        } else {
            response.getWriter().write("异常:" + accessDeniedException.getMessage());
        }
    }
}
