package com.wenmxr.pojo;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	private String orderId;
	private Double orderMoney;
	private String orderReceiverinfo;
	private Integer orderState;
	private Timestamp orderTime;
	private String userId;
	private Timestamp orderPayTime;
	//对多的关联表格的相关属性
	private List<OrderItem> orderItems;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getOrderReceiverinfo() {
		return orderReceiverinfo;
	}
	public void setOrderReceiverinfo(String orderReceiverinfo) {
		this.orderReceiverinfo = orderReceiverinfo;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getOrderPayTime() {
		return orderPayTime;
	}
	public void setOrderPayTime(Timestamp orderPayTime) {
		this.orderPayTime = orderPayTime;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderMoney=" + orderMoney + ", orderReceiverinfo=" + orderReceiverinfo
				+ ", orderState=" + orderState + ", orderTime=" + orderTime + ", userId=" + userId + ", orderPayTime="
				+ orderPayTime + ", orderItems=" + orderItems + "]";
	}
	
}
