package com.wenmxr.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.wenmxr.cart.mapper")
@EnableEurekaClient
public class StarterCart {

	public static void main(String[] args) {
		SpringApplication.run(StarterCart.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate initRest() {
		return new RestTemplate();
	}

}
