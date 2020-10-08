package com.welearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableCaching
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class WelearnRouteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelearnRouteServerApplication.class, args);
	}
}
