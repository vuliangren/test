package com.welearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableEurekaServer
@SpringCloudApplication
public class WelearnDiscoveryServerApplication {

	public static void main(String[] args) { 
		SpringApplication.run(WelearnDiscoveryServerApplication.class, args);
	}
}
