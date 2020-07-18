package com.igeek.ssm.interceptor;

import com.igeek.ssm.exception.LoginException;
import javafx.fxml.LoadException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在执行handler之前：登录拦截、权限拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url= request.getRequestURI();
        System.out.println(url);

        if(url.indexOf("login")>=0 || url.indexOf("regist")>=0 || url.indexOf("validata")>=0){
            return true;
        }

        HttpSession session=request.getSession();
        Object user = session.getAttribute("user");
        if(user!=null){
            return true;
        }
        //false 代表不放行  true放行
        throw new LoginException("该资源必须登录后访问");
    }
    //在执行handler之后，在返回ModelAndView对象之前：统一处理数据和视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //在执行handler之后：异常处理，日志处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
