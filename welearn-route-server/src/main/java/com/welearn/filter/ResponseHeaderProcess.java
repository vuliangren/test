package com.welearn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.welearn.dictionary.common.RequestAttributeConst;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.error.exception.ApiAuthCheckFailedException;
import com.welearn.interceptor.ResponseBodyProcess;
import com.welearn.service.RouteCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * Description : 客户端请求的用户身份认证
 * Created by Setsuna Jin on 2018/1/1.
 */
@Slf4j
@Component
public class ResponseHeaderProcess extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        return !RequestMethod.OPTIONS.name().equals(request.getMethod());
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        // ZUUL 默认会过滤掉一些敏感的请求头
        // authorization、set-cookie、cookie、host、connection、content-length、content-encoding、server、transfer-encoding、x-application-context
        // 统计响应所花费时间
        Object requestInTime = ctx.getZuulRequestHeaders().get(RequestHeaderConst.REQUEST_IN_TIME.getHeaderName());
        if (Objects.nonNull(requestInTime)){
            long costTime = Long.parseLong(requestInTime.toString()) - new Date().getTime();
            response.setHeader(RequestHeaderConst.RESPONSE_COST_TIME.getHeaderName(), costTime + " ms");
        }
        // 处理跨域相关响应头
        ResponseBodyProcess.processCrossOrigin(request,response);
        return null;
    }
}
