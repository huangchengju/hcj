package com.qf.hcj.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlackInterceptor implements HandlerInterceptor {
    String path="regist.do#queryname.do#queryemail.do#login.do";
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object ue = request.getSession().getAttribute("ue");
        if(null==ue){
        if (path.contains(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1))){
            return true;
        }
        return false;
        }else {
            return true;
        }

    }
}
