package com.wenmxr.order.service;

import com.wenmxr.order.mapper.OrderMapper;
import com.wenmxr.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	public List<Order> queryMyOrders(String userId) {
		return orderMapper.selectOrdersByUserId(userId);
	}
	public void addOrder(Order order) {
		//新增的数据order对象,缺少一些内容
		//orderId orderPaystate(0) orderTime(new Date())
		order.setOrderId(UUID.randomUUID().toString());
		Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		order.setOrderTime(orderTime);
		order.setOrderState(0);
		System.out.println(order);
		orderMapper.insertOrderAndOrderItems(order);
		String userId = order.getUserId();
		orderMapper.deleteCartByUser(userId);
	}
	public void deleteOrder(String orderId) {
		orderMapper.deleteOrder(orderId);
		
	}

}
