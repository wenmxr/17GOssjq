package com.wenmxr.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.wenmxr.seckill.mapper")
@EnableEurekaClient
public class StarterSeckill {
	public static void main(String[] args) {
		SpringApplication.run(StarterSeckill.class, args);
	}
}




