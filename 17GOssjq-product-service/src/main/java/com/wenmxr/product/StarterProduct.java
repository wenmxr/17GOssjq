package com.wenmxr.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: 17GOssjq
 * @description: 商品系统
 * @author: XuDeming
 * @date: 2020-03-13 21:45:51
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wenmxr.product.mapper")
public class StarterProduct {
    public static void main(String[] args) {
        SpringApplication.run(StarterProduct.class, args);
    }
}
