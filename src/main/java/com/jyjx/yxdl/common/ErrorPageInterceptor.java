package com.jyjx.yxdl.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (response.getStatus() == 404) {
            //捕获异常后进行重定向，controller对应的requestMapping为/error/{code}
            response.sendRedirect("/admin/index");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
