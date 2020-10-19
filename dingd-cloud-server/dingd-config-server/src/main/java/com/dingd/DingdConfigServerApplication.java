package com.dingd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DingdConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingdConfigServerApplication.class, args);
    }

}
