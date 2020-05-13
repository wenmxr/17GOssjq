package com.wenmxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
// 和@EnableEurekaServer的区别在与仅仅具有发现注册的能力
// @EnableDiscoveryClient 仅仅具有发现的能力
@EnableZuulProxy
public class StarterZuul {

	public static void main(String[] args) {
		SpringApplication.run(StarterZuul.class, args);
	}

}
