package com.example.appDemo.advice;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by xu.yin on 9/7/17.
 */
@Component
@Aspect
@Slf4j
public class AccessLogAdvice {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping(){}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController(){}



    @Around("requestMapping()||restController()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("before method:{},{}",joinPoint.getSignature().toLongString(),request.getServletPath());
        Object retVal = joinPoint.proceed();
        log.info("after method:{}", joinPoint.getSignature().toShortString());
        return  retVal;
    }
}
