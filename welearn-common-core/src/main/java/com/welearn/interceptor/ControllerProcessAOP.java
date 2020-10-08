package com.welearn.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description : 控制器方法追踪
 * Created by Setsuna Jin on 2018/1/31.
 */
@Slf4j
@Aspect
@Component
public class ControllerProcessAOP {

    @Value("${debug}")
    private Boolean isDebug;

    @Around("execution(* com.welearn.controller..*(..)) && @annotation(requestMapping)")
    public Object Interceptor(ProceedingJoinPoint point, RequestMapping requestMapping) throws Throwable {
        if (!isDebug)
            return point.proceed();
        // 开发环境下执行
        long processTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Object result;
        log.info("Controller-Process-Method:{}.{}", methodSignature.getMethod().getDeclaringClass().getName(),methodSignature.getMethod().getName());
        Object[] methodArguments = point.getArgs();
        for (int i = 0; i < methodArguments.length; i++) {
            Object object = methodArguments[i];
            if (object instanceof HttpServletRequest)
                methodArguments[i] = "HttpServletRequest";
            else if (object instanceof HttpServletResponse)
                methodArguments[i] = "HttpServletResponse";
        }
        try {
            log.info("Controller-Process-Args:{}", JSON.toJSONString(methodArguments));
        } catch (Exception e){
            log.info("Controller-Process-Args:{}", "Parse Json Failed");
        }
        try {
            result = point.proceed();
        } catch (Throwable throwable){
            throwable.printStackTrace();
            log.error("Controller-Process-Error:{}", JSON.toJSONString(throwable.getStackTrace()[0].getClassName()));
            throw throwable;
        }
        log.info("Controller-Process-Result:{}", JSON.toJSONString(result));
        log.info("Controller-Process-Cost:{}", System.currentTimeMillis() - processTime + " ms");
        return result;
    }
}
