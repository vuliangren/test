package com.welearn;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRabbit
@EnableCaching
@EnableFeignClients
@EnableScheduling
@EnableTransactionManagement
@SpringCloudApplication
public class WelearnStorageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelearnStorageServerApplication.class, args);
	}
}
