package com.welearn.config;

import com.welearn.util.GlobalContextUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.welearn.dictionary.authorization.RequestTypeConst.REQUEST_FROM_ROUTE;
import static com.welearn.dictionary.authorization.RequestTypeConst.REQUEST_FROM_SYSTEM_INSIDE;
import static com.welearn.dictionary.common.RequestHeaderConst.AUTH_REUSLT;
import static com.welearn.dictionary.common.RequestHeaderConst.REQUEST_TYPE;
import static com.welearn.dictionary.common.RequestHeaderConst.VEDA_DEPARTMENT_ID;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/15.
 */
@Configuration
public class FeignConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public RequestInterceptor withTokenRequestInterceptor(){
        return new TokenRequestInterceptor(applicationName);
    }

    @AllArgsConstructor
    public static class TokenRequestInterceptor implements RequestInterceptor {

        @Setter
        private String applicationName;

        @Override
        public void apply(RequestTemplate requestTemplate) {
            requestTemplate.header("Request-Feign-Application", applicationName);
            try {
                requestTemplate.header(AUTH_REUSLT.getHeaderName(), GlobalContextUtil.getAuthResultHeader());
                requestTemplate.header(VEDA_DEPARTMENT_ID.getHeaderName(), GlobalContextUtil.getCurrentDepartmentId());
                requestTemplate.header(REQUEST_TYPE.getHeaderName(), REQUEST_FROM_ROUTE.name());
            } catch (Exception e){
                requestTemplate.header(REQUEST_TYPE.getHeaderName(), REQUEST_FROM_SYSTEM_INSIDE.name());
            }
        }
    }

    @Bean
    Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }
}
