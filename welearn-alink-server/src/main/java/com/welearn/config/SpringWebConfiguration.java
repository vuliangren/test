package com.welearn.config;

import com.welearn.interceptor.RequestUserHandler;
import com.welearn.interceptor.ServletProcessTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class SpringWebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletProcessTracker servletProcessTracker;

    /**
     * 添加拦截器
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(servletProcessTracker).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestUserHandler());
    }
}
