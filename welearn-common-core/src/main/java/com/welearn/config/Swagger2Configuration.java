package com.welearn.config;

import com.welearn.dictionary.api.ServiceConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description : Swagger2 配置
 * 在生产测试环境下禁用此功能 仅在开发模式下打开
 * Created by Setsuna Jin on 2018/1/16.
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class Swagger2Configuration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;

    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .host("192.168.1.101:" + port)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.welearn.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo(){
        String serviceName = ServiceConst.get(applicationName).getServiceName();
        return new ApiInfoBuilder()
                .title(serviceName.toUpperCase() + " SERVICE")
                .description("welearn service - " + serviceName + " server api")
                .termsOfServiceUrl("http://ryme.shebei120.com")
                .contact(new Contact("Setsuna·Jin",
                        "https://github.com/HelloSetsuna",
                        "hellosetsuna@outlook.com"))
                .build();

    }
}
