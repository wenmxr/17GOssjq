package com.wenmxr.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenmxr.vo.SysResult;
import com.wenmxr.product.service.CreateIndexService;

@RestController
public class CreateIndex {
	@Autowired
	private CreateIndexService cis;
	//调用请求创建索引数据
	@RequestMapping("product/createIndex")
	public SysResult createIndex(){
		try{
			cis.createIndex();
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "新增索引失败", null);
		}
	}
}
