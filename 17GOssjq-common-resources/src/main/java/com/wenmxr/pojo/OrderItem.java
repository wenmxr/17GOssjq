package com.wenmxr.pojo;

public class OrderItem {
	private Integer itemId;
	private String orderId;
	private String productId;
	private Integer num;
	private String productImg1url;
	private String productName;
	private Double productPrice;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getProductImg1url() {
		return productImg1url;
	}
	public void setProductImg1url(String productImg1url) {
		this.productImg1url = productImg1url;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", orderId=" + orderId + ", productId=" + productId + ", num=" + num
				+ ", productImg1url=" + productImg1url + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}
	
  }
