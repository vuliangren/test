package com.welearn.interceptor;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.authorization.RequestTypeConst;
import com.welearn.dictionary.common.RequestAttributeConst;
import com.welearn.dictionary.common.RequestHeaderConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ServletProcessTracker implements HandlerInterceptor {

    @Value("${debug}")
    private Boolean isDebug;

    @Value("${spring.application.name}")
    private String processServerName;

    @Value("${server.port}")
    private String processServerPort;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(RequestAttributeConst.PROCESS_START_TIME.getName(), System.currentTimeMillis());
        if (isDebug){
            log.info("Server-Process-Server:{}:{}", processServerName, processServerPort);
            log.info("Server-Process-Request:{}:{}", request.getMethod(), request.getRequestURL());
            log.info("Server-Process-content:{}", request.getQueryString());
            String requestFeignApplication = request.getHeader(RequestHeaderConst.REUQEST_FEIGN_APPLICATION.getHeaderName());
            if (StringUtils.isNotBlank(requestFeignApplication))
                log.info("Request-Feign-Application:{}", requestFeignApplication);
            String requestType = request.getHeader(RequestHeaderConst.REQUEST_TYPE.getHeaderName());
            log.info("Request-Type:{}", StringUtils.isBlank(requestType)? RequestTypeConst.REQUEST_FROM_ROUTE.name():requestType);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (isDebug){
            log.info("Server-Process-Cost-Time:{}", System.currentTimeMillis() -
                    (Long)request.getAttribute(RequestAttributeConst.PROCESS_START_TIME.getName()) + " ms");
        }
    }
}
