package com.wenmxr.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StarterSearch {
	public static void main(String[] args) {
		SpringApplication.run(StarterSearch.class, args);
	}
}




