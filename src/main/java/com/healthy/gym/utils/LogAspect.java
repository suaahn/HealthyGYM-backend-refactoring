package com.healthy.gym.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect // Aspect : 부가 기능 구현체들을 포함하고 있는 모듈
@Component
public class LogAspect {

    /* controller 패키지에 포함된 public 메서드와 매칭 */
    @Pointcut("within(com.healthy.gym.controller..*)")
    public void onRequest() {}

    /* Pointcut 과 매칭되는 메서드의 실행 전, 후에 실행
     *  @Around advice 는 꼭 proceed()가 필요하다. */
    @Around("onRequest()")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable{

        Class clazz = joinPoint.getTarget().getClass();
        Logger logger = LoggerFactory.getLogger(clazz);

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object result = null;

        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            return result;
        } finally {
            logger.info(request.getMethod() + " " + request.getRequestURI());
            //logger.info("Response " + new JSONObject(result).toString());
        }
    }

}
