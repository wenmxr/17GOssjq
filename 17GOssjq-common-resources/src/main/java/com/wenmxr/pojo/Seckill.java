package com.wenmxr.pojo;

import java.sql.Timestamp;

public class Seckill {
	private Long seckillId;
	private String name;
	private Integer number;
	private Long InitialPrice;
	private Long seckillPrice;
	private String sellPoint;
	private Timestamp createTime;
	private Timestamp startTime;
	private Timestamp endTime;
	public Long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Long getInitialPrice() {
		return InitialPrice;
	}
	public void setInitialPrice(Long initialPrice) {
		InitialPrice = initialPrice;
	}
	public Long getSeckillPrice() {
		return seckillPrice;
	}
	public void setSeckillPrice(Long seckillPrice) {
		this.seckillPrice = seckillPrice;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	 
	
}
