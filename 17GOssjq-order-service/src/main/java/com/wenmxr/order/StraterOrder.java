package com.wenmxr.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@MapperScan("com.wenmxr.order.mapper")
@EnableEurekaClient
public class StraterOrder {
	public static void main(String[] args) {
		SpringApplication.run(StraterOrder.class, args);
	}
}
