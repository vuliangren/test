package com.welearn.interceptor;

import com.welearn.annotation.RequestCompany;
import com.welearn.annotation.RequestUser;
import com.welearn.util.GlobalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/4.
 */
@Slf4j
public class RequestCompanyHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestCompany.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return GlobalContextUtil.getAuthResult().getCompany();
    }
}
