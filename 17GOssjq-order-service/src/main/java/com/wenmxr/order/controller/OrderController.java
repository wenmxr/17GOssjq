package com.wenmxr.order.controller;

import com.wenmxr.order.service.OrderService;
import com.wenmxr.pojo.Order;
import com.wenmxr.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order/manage")
public class OrderController {
	@Autowired
	private OrderService orderService;
	//查询我的订单
	@RequestMapping("/query/{userId}")
	public List<Order> queryMyOrders(@PathVariable
			String userId){
		return orderService.queryMyOrders(userId);	
	}
	//新增我的订单
	@RequestMapping("/save")
	public SysResult addOrder(Order order){
		try{
			System.out.println(order);
			orderService.addOrder(order);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "新增订单失败", null);
		}
	}
	//删除订单
	@RequestMapping("/delete/{orderId}")
	public SysResult deleteOrder(@PathVariable String
			orderId){
		try{
			orderService.deleteOrder(orderId);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "删除订单失败", null);
		}
	}
}
