package com.wenmxr.search.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wenmxr.pojo.Product;
import com.wenmxr.utils.MapperUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
	@Autowired
	private TransportClient client;
	public List<Product> search(String text, Integer page, Integer rows) throws JsonParseException, JsonMappingException, IOException {
		//使用transportclient封装query对象查询
		MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(text, "productName","product_category1","product_category2","productDescription");
		//prepare获取request对象
		SearchRequestBuilder request = client.prepareSearch("17go");
		//query,start,rows
		SearchResponse response = request.setQuery(query).setFrom((page-1)*rows).setSize(rows).get();
		//response中有需要的查询结果
		List<Product> pList=new ArrayList<Product>();
		//获取hits 循环解析到pList
		SearchHit[] hits = response.getHits().getHits();
		for (SearchHit hit : hits) {
			String pJson=hit.getSourceAsString();
			Product p=MapperUtil.MP.readValue(pJson, Product.class);
			pList.add(p);
		}
		return pList;
	}

}
