package com.welearn.config;

import com.qiniu.util.Auth;
import com.welearn.interceptor.RequestUserHandler;
import com.welearn.interceptor.ServletProcessTracker;
import com.welearn.xdoc.XDocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Slf4j
@Configuration
public class SpringWebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletProcessTracker servletProcessTracker;

    @Value("${qiniu.access-key}")
    private String accessKey;

    @Value("${qiniu.secret-key}")
    private String secretKey;


    @Value("${xdoc.url}")
    private String xDocUrl;

    @Value("${xdoc.key}")
    private String xDocKey;


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


    @Bean
    public XDocService getXDocService() {
        return new XDocService(xDocUrl, xDocKey);
    }

    @Bean
    public Auth getAuth(){
        return Auth.create(accessKey, secretKey);
    }
}
