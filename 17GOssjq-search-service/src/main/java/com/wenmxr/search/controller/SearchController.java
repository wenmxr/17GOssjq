package com.wenmxr.search.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wenmxr.pojo.Product;
import com.wenmxr.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {
	@Autowired
	private SearchService searcher;
	//搜索功能
	@RequestMapping("/search/manage/query")
	public List<Product> search(
			//访问方式：http://localhost:1706/search/manage/query?query=%E6%96%B0%E6%AC%BE&page=1&rows=4
			//@RequestParam("query")里面的值（query）和浏览器地址栏的参数名相同
			//代码表示把接受的前端传过来的qery值赋给text，进行后端查询。
			@RequestParam("query")String text,Integer page,Integer rows) throws JsonParseException, JsonMappingException, IOException{
		return searcher.search(text,page,rows);
	}
	
	
	
	
	
}
