package com.welearn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.interceptor.ResponseBodyProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Description : 客户端请求的用户身份认证
 * Created by Setsuna Jin on 2018/1/1.
 */
@Slf4j
@Component
public class OptionsRequestProcess extends ZuulFilter {

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
        return RequestMethod.OPTIONS.name().equals(request.getMethod());
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(SC_OK);
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        // 处理跨域相关响应头
        ResponseBodyProcess.processCrossOrigin(request,response);
        return null;
    }
}
