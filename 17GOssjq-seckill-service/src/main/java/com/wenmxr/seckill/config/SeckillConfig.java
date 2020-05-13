package com.wenmxr.seckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeckillConfig {
	//这些对象,在程序启动时,就被spring加载到内存了
	//但是直到生产端第一次连接rabbitmq发送消息
	//或者消费端监听消息队列 rabbitmq在springboot底层
	//连接才会获取内存对象创建对应关系
	//声明一个队列
	@Bean
	public Queue queue01(){
		//channel.queueDeclare
		return new Queue("seckillq", true, false, false, null);
	}
	//声明一个交换机
	@Bean
	public DirectExchange ex(){
		//channel.exchangeDeclare
		return new DirectExchange("seckillEx", true, false, null);
	}
	//声明一个队列与交换机的绑定关系
	@Bean
	public Binding bind01(){
		//channle.queueBind(ex,routingkey)
		return BindingBuilder.bind(queue01()).to(ex()).with("seckill");
	}
	
	
	
	
	
}
