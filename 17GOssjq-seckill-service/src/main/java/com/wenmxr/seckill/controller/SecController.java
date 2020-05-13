 package com.wenmxr.seckill.controller;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenmxr.pojo.Seckill;
import com.wenmxr.vo.SysResult;
import com.wenmxr.seckill.service.SecService;

@RestController
@RequestMapping("/seckill/manage")
public class SecController {
	//查询所有秒杀商品
	//select * from seckill List<Seckill>
	@Autowired
	private SecService secService;
	@RequestMapping("list")
	public List<Seckill> queryAll(){
		return secService.queryAll();
	}
	//根据商品id查询单个商品对象
	@RequestMapping("detail")
	public Seckill queryOne(Integer seckillId){
		return secService.queryOne(seckillId);
	}
	@Autowired
	private RabbitTemplate rabbitTemplate;
	//接收秒杀请求
	@RequestMapping("{seckillId}")
	public SysResult sendMsg(@PathVariable Integer seckillId){
		//模拟每次请求生成的不同用户 userPhone
		String userPhone="1889900"+RandomUtils.nextInt(1000, 9999);
		//生成携带的消息信息
		String msg=userPhone+"/"+seckillId;
		//template的convertAndSend不需要关心byte[]数据
		try{
			/*//channel.basicPublish
			MessageProperties msgp=new MessageProperties();
			msgp.setPriority(1);
			msgp.setExpiration("");
			Message h=new Message(msg.getBytes(), null);
			MessageProperties msgp=new MessageProperties();
			msgp.setPriority(1);
			msgp.setPriority(1);
			msgp.setExpiration("");
			msgp.setClusterId("");
			msgp.setUserId("");
			Message h=new Message(msg.getBytes(), msgp);
			rabbitTemplate.send("seckillEx", "seckill", h);*/
			rabbitTemplate.convertAndSend("seckillEx", "seckill", msg);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
		
	}
	
	
	
	
	
	
	
	
}
 