package com.wenmxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: 17GOssjq
 * @description: Eureka注册中心
 * @author: XuDeming
 * @date: 2020-03-13 21:28:26
 **/
@SpringBootApplication
@EnableEurekaServer
public class StarterEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(StarterEurekaServer.class, args);
    }
}
