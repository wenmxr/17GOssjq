package com.wenmxr.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: 17GOssjq
 * @description:
 * @author: XuDeming
 * @date: 2020-04-11 18:11:15
 **/
@SpringBootApplication
@MapperScan("com.wenmxr.user.mapper")
@EnableEurekaClient
public class StarterUser {
    public static void main(String[] args) {
        SpringApplication.run(StarterUser.class, args);
    }
}
