package com.wenmxr.pojo;

import java.sql.Timestamp;

public class Product {
	//根据驼峰命名定义属性
	
	private String  productId;
	private String  productName;
	//封装类完成基本类型的使用
	//满足业务意义
	private Double  productPrice;
	private String  productCategory1;
	private String  productCategory2;
	private Integer productNum;
	private String  productImg1url;
	private String  productImg2url;
	private String  productImg3url;
	private String  productImg4url;
	private String  productImg5url;
	private String  productDescription;
	private Timestamp productAddtime;
	//getter setter
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductCategory1() {
		return productCategory1;
	}
	public void setProductCategory1(String productCategory1) {
		this.productCategory1 = productCategory1;
	}
	public String getProductCategory2() {
		return productCategory2;
	}
	public void setProductCategory2(String productCategory2) {
		this.productCategory2 = productCategory2;
	}
	public String getProductImg1url() {
		return productImg1url;
	}
	public void setProductImg1url(String productImg1url) {
		this.productImg1url = productImg1url;
	}
	public String getProductImg2url() {
		return productImg2url;
	}
	public void setProductImg2url(String productImg2url) {
		this.productImg2url = productImg2url;
	}
	public String getProductImg3url() {
		return productImg3url;
	}
	public void setProductImg3url(String productImg3url) {
		this.productImg3url = productImg3url;
	}
	public String getProductImg4url() {
		return productImg4url;
	}
	public void setProductImg4url(String productImg4url) {
		this.productImg4url = productImg4url;
	}
	
	public Timestamp getProductAddtime() {
		return productAddtime;
	}
	public void setProductAddtime(Timestamp productAddtime) {
		this.productAddtime = productAddtime;
	}
	public String getProductImg5url() {
		return productImg5url;
	}
	public void setProductImg5url(String productImg5url) {
		this.productImg5url = productImg5url;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productCategory1=" + productCategory1 + ", productCategory2=" + productCategory2 + ", productNum="
				+ productNum + ", productImg1url=" + productImg1url + ", productImg2url=" + productImg2url
				+ ", productImg3url=" + productImg3url + ", productImg4url=" + productImg4url + ", productImg5url="
				+ productImg5url + ", productDescription=" + productDescription + ", productAddtime=" + productAddtime
				+ "]";
	}
	
	
	
}
