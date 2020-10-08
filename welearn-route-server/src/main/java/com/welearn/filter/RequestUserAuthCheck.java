package com.welearn.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.dictionary.error.ErrorMessageConst;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.Signature;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.ApiAuthCheckFailedException;
import com.welearn.error.exception.PermissionCheckFailedException;
import com.welearn.feign.authorization.TokenFeignClient;
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
import java.io.PrintWriter;

import static com.welearn.dictionary.common.RequestHeaderConst.VEDA_TOKEN;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * Description : 客户端请求的用户身份认证
 * Created by Setsuna Jin on 2018/1/1.
 */
@Slf4j
@Component
public class RequestUserAuthCheck extends ZuulFilter {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RouteCacheService routeCacheService;

    @Autowired
    private TokenFeignClient tokenFeignClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 记录请求开始时间
        ctx.addZuulRequestHeader(RequestHeaderConst.REQUEST_IN_TIME.getHeaderName(), ""+System.currentTimeMillis());
        String pathInfo = request.getRequestURI();
        // 不处理 OPTIONS 请求
        return !(RequestMethod.OPTIONS.name().equals(request.getMethod()) ||
                // 不处理 登陆认证 请求
                StringUtils.startsWith(pathInfo, "/api/authorization/") ||
                // 不处理 Antd前端路由详情信息获取 请求
                StringUtils.startsWith(pathInfo, "/api/common/reRouteRole/routeInfos") ||
                // 不处理 七牛回调 请求
                StringUtils.startsWith(pathInfo, "/api/storage/public/") ||
                // 不处理 阿里物联云开放接口 请求
                StringUtils.startsWith(pathInfo, "/api/alink/public/") ||
                // 不处理 阿里云短信回调 请求
                StringUtils.startsWith(pathInfo, "/api/notify/public/"));
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 获取 客户端请求头的TOKEN
        String accessToken = request.getHeader(VEDA_TOKEN.getHeaderName());
        String permissionCode = request.getRequestURI().replace("/api/","/");
        try {
            if (StringUtils.isBlank(accessToken))
                throw new ApiAuthCheckFailedException("Request header need to have auth token");
            String routeAuthCacheKey = routeCacheService.routeAuthCache(accessToken);
            if (StringUtils.isBlank(routeAuthCacheKey))
                throw new ApiAuthCheckFailedException("auth token is invalid, please re login");
            // 获取权限列表进行比对判断
            if (!routeCacheService.permissions(routeAuthCacheKey).contains(permissionCode))
                throw new PermissionCheckFailedException("You have no permission to request this uri");
            ctx.addZuulRequestHeader(RequestHeaderConst.AUTH_REUSLT.getHeaderName(), routeAuthCacheKey);
        }
        catch (PermissionCheckFailedException e){
            Signature signature = new Signature(ErrorMessageConst.PERMISSION_CHECK_FAILED);
            sendCommonResponse(ctx, 403, signature.toErrorResponse(e.getMessage()));
        }
        catch (Exception e){
            Signature signature = new Signature(ErrorMessageConst.API_AUTH_CHECK_FAILED);
            sendCommonResponse(ctx, 401, signature.toErrorResponse(e.getMessage()));
        }
        return null;
    }

    private void sendCommonResponse(RequestContext context, int status, CommonResponse commonResponse){
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(status);
        HttpServletResponse response = context.getResponse();
        response.setContentType("application/json; charset=utf8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSON.toJSONString(commonResponse));
        } catch (IOException e) {
            log.error("异常处理", e);
        }
    }
}
