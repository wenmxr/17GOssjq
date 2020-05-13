package com.wenmxr.order.mapper;

import com.wenmxr.pojo.Order;

import java.util.List;

public interface OrderMapper {

	List<Order> selectOrdersByUserId(String userId);

	void insertOrderAndOrderItems(Order order);

	void deleteOrder(String orderId);

	void deleteCartByUser(String userId);
}