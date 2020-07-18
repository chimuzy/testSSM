package com.igeek.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
             HttpServletResponse response, Object handler, Exception e) {

        ModelAndView mv=new ModelAndView();
        LoginException ex=null;
        if(e instanceof LoginException){
            ex=(LoginException) e;
            mv.addObject("msg",ex.getMessage());
            mv.setViewName("../login.jsp");
        }else{
            ex=new LoginException("未知错误");
            mv.addObject("msg",ex.getMessage());
            mv.setViewName("../error.jsp");
        }
        return mv;
    }
}
