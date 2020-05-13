package com.wenmxr.seckill.consumer;

import com.wenmxr.pojo.Success;
import com.wenmxr.seckill.mapper.SecMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class SeckillConsumert {
	@Autowired
	private SecMapper secMapper;

	//编写一个接收消息的方法
	//使用注解实现对队列的监听 seckillq队列实现异步消费
	@RabbitListener(queues="seckillq")
	public void consume(String msg){
		Jedis jd = new Jedis("47.99.106.114",6379);
		jd.auth("sun+moon");
		//msg就是消费队列 seckillq中的一条消息
		//msg=18099995279/1
		/*秒杀的客户执行减库存
		 * 如果减库存成功,说明秒杀成功(线程高并发安全问题)
		 * 入库记录成功的用户和商品的对应关系 
		 */
		//解析出 userPhone和seckillId
		Long userPhone=Long.parseLong(msg.split("/")[0]);
		Long seckillId=Long.parseLong(msg.split("/")[1]);
		//减库存 seckil记录商品信息的表格的number字段
		//update seckill set number=number-1
		//where number>0 {nowtime}<endTime nowtime>startTime
		//seckill_id=#{seckill_id}
		//创建于库存数量有关的list对象
		String seckillList="sec_list_"+seckillId;
		//读取数据库 获取库存数量 当list不存在时
		if(!jd.exists(seckillList)){
			for(int i=0;i<100;i++){
			jd.lpush(seckillList, "1");}
		}
		Date nowTime=new Date();
		//获取秒杀减库存的权限
		String rpop = jd.rpop(seckillList);
		if(rpop==null){
			//抢完了,秒杀失败return后续不执行
			//没有更新成功,秒杀失败
			System.out.println("当前用户:"+userPhone+".秒杀失败");
			return;
		}
		int updateR=secMapper.updateNumberBySeckillCondition(seckillId,nowTime);
		//判断成功失败
		if(updateR==0){
			//没有更新成功,秒杀失败
			System.out.println("当前用户:"+userPhone+".秒杀失败");
			return;
		}//秒杀成功,入库成功信息
		Success suc=new Success();
		//补充完毕属性
		suc.setCreateTime((Timestamp) nowTime);
		suc.setSeckillId(seckillId);
		suc.setState(1);
		suc.setUserPhone(userPhone);
		secMapper.insertSuccess(suc);
	}
}
