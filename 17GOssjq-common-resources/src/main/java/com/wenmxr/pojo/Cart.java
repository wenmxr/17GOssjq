package com.wenmxr.pojo;

import java.io.Serializable;

public class Cart implements Serializable {
	private Integer cartId;
	private String userId;
	private String productId;
	private String productImg1url;
	private String productName;
	private Double productPrice;
	private Integer num;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", productId=" + productId + ", productImg1url="
				+ productImg1url + ", productName=" + productName + ", productPrice=" + productPrice + ", num=" + num
				+ "]";
	}
	
	
}
