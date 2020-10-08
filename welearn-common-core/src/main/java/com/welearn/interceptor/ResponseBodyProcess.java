package com.welearn.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.welearn.dictionary.common.AccessControlConst;
import com.welearn.dictionary.common.RequestAttributeConst;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.entity.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@ControllerAdvice("com.welearn.controller")
public class ResponseBodyProcess implements ResponseBodyAdvice<CommonResponse> {

    @Value("${spring.application.name}")
    private String processServerName;

    @Value("${server.port}")
    private String processServerPort;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return CommonResponse.class.getName().equals(returnType.getParameterType().getName());
    }

    @Override
    public CommonResponse beforeBodyWrite(CommonResponse body, MethodParameter returnType, MediaType selectedContentType,
                                          Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpRequest ssReq = (ServletServerHttpRequest)request;
        ServletServerHttpResponse ssResp = (ServletServerHttpResponse)response;
        if(Objects.isNull(ssReq) || Objects.isNull(ssResp))
            return body;
        HttpServletRequest req = ssReq.getServletRequest();
        HttpServletResponse resp = ssResp.getServletResponse();
        if(Objects.isNull(req) || Objects.isNull(resp))
            return body;
        // 处理响应头
        processHeader(req, resp);
        // 处理分页
        processPaginate(req, body);
        // 返回控制器响应
        return body;
    }

    private void processHeader(HttpServletRequest req, HttpServletResponse resp){
        // 添加服务监控响应头
        processServerTrack(req, resp);
        // 添加JSON类型响应头
        resp.setHeader("Content-Type","application/json;charset=UTF-8");
        // 已在 Route 处进行处理
        // 添加跨域相关响应头
        // processCrossOrigin(req, resp);
    }

    private void processPaginate(HttpServletRequest req, CommonResponse body){
        // 分页处理
        try {
            Object pageInfo = req.getAttribute(RequestAttributeConst.CACHE_PAGE_INFO.getName());
            if (Objects.nonNull(pageInfo)){
                body.setPagination((PageInfo)pageInfo);
            }
        } catch (Exception e){
            log.error("Process request cached pageInfo failed:{}", e.getMessage());
        }
    }

    /**
     * 配置处理的服务和时间响应头
     * @param req 请求
     * @param resp 响应
     */
    private void processServerTrack(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader(RequestHeaderConst.PROCESS_SERVER.getHeaderName(), processServerName + ":" + processServerPort);
        Object processStartTime = req.getAttribute(RequestAttributeConst.PROCESS_START_TIME.getName());
        if (Objects.nonNull(processStartTime))
            resp.setHeader(RequestHeaderConst.PROCESS_COST_TIME.getHeaderName(),
                    System.currentTimeMillis() - (Long)processStartTime + " ms");
    }

    /**
     * 配置跨域请求的响应头
     * @param req 请求
     * @param resp 响应
     */
    public static void processCrossOrigin(HttpServletRequest req, HttpServletResponse resp){
        String origin = req.getHeader("Origin");
        String referer = req.getHeader("Referer");
        if(StringUtils.isBlank(origin)) {
            if(StringUtils.isNotBlank(referer)) {
                int index = referer.indexOf("/", 8);
                origin = index == -1 ? referer : referer.substring(0, index);
            }
            else
                origin = AccessControlConst.AccessControlAllowOrigin.getHeaderValue();
        }
        // 设置响应值
        resp.setHeader(AccessControlConst.AccessControlAllowOrigin.getHeaderName(), origin);
        resp.setHeader(AccessControlConst.AccessControlExposeHeaders.getHeaderName(), AccessControlConst.AccessControlExposeHeaders.getHeaderValue());
        resp.setHeader(AccessControlConst.AccessControlMaxAge.getHeaderName(), AccessControlConst.AccessControlMaxAge.getHeaderValue());
        resp.setHeader(AccessControlConst.AccessControlAllowCredentials.getHeaderName(), AccessControlConst.AccessControlAllowCredentials.getHeaderValue());
        resp.setHeader(AccessControlConst.AccessControlAllowMethods.getHeaderName(), AccessControlConst.AccessControlAllowMethods.getHeaderValue());
        resp.setHeader(AccessControlConst.AccessControlAllowHeaders.getHeaderName(), AccessControlConst.AccessControlAllowHeaders.getHeaderValue());
    }
}
