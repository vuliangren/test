package com.welearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class WelearnConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelearnConfigServerApplication.class, args);
	}
}
