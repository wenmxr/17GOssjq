package com.wenmxr.pojo;

public class Address {
	private Integer addrId;
	private String userId;
	private String addrUsername;
	private String addrProvice;
	private String addrCity;
	private String addrCounty;
	private String addrArea;
	private String addrZip;
	private String addrPhone;
	@Override
	public String toString() {
		return "Address [addrId=" + addrId + ", userId=" + userId + ", addrUsername=" + addrUsername + ", addrProvice="
				+ addrProvice + ", addrCity=" + addrCity + ", addrCounty=" + addrCounty + ", addrArea=" + addrArea
				+ ", addrZip=" + addrZip + ", addrPhone=" + addrPhone + "]";
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddrUsername() {
		return addrUsername;
	}
	public void setAddrUsername(String addrUsername) {
		this.addrUsername = addrUsername;
	}
	public String getAddrProvice() {
		return addrProvice;
	}
	public void setAddrProvice(String addrProvice) {
		this.addrProvice = addrProvice;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getAddrCounty() {
		return addrCounty;
	}
	public void setAddrCounty(String addrCounty) {
		this.addrCounty = addrCounty;
	}
	public String getAddrArea() {
		return addrArea;
	}
	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}
	public String getAddrZip() {
		return addrZip;
	}
	public void setAddrZip(String addrZip) {
		this.addrZip = addrZip;
	}
	public String getAddrPhone() {
		return addrPhone;
	}
	public void setAddrPhone(String addrPhone) {
		this.addrPhone = addrPhone;
	}
}
