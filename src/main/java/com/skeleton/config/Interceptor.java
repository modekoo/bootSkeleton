package com.skeleton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Interceptor implements HandlerInterceptor {

    public Interceptor(){}

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler){

        log.debug("request URL : {}", req.getRequestURL());

        return true;
    }

}
