package com.wenmxr.product.service;

import com.wenmxr.pojo.Product;
import com.wenmxr.product.mapper.ProductMapper;
import com.wenmxr.utils.MapperUtil;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateIndexService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private TransportClient client;
	public void createIndex() throws Exception {
		/*读取源数据 select * from t_product limit 0,100
		 *List<Product>
		 *整理成document 通过调用transportclient写入到索引中
		 *创建索引,for循环list挨个写入 product-->json
		 */
		List<Product> pList = productMapper.queryProducts(0,100);
		//创建索引 easymall
		//client.admin().indices().prepareCreate("17go").get();
		//client.admin().indices().prepareUpgrade("17go").get();
		//for循环添加document
		if(client.admin().indices().exists(//判断索引是否存在，存在返回true，执行更新逻辑()；否则，新增
				new IndicesExistsRequest().indices(
						new String[]{"17go"})).actionGet().isExists()){
			//client.admin().indices().prepareUpgrade("17go").get();
			
			for (Product product : pList) {
				String json = MapperUtil.MP.writeValueAsString(product);
				IndexRequestBuilder request = 
				client.prepareIndex("17go", "product", product.getProductId());
				//client.prepareUpdate("17go", "product", product.getProductId());
				//添加request参数 json
				//request.setFetchSource(json).get();
				request.setSource(json).get();
				//先把product序列化成json
				//String id=product.getProductId();
				//String json = MapperUtil.MP.writeValueAsString(product);
				//UpdateRequest request = new UpdateRequest("17go", "product", id);
				//client.prepareIndex("17go", "product", product.getProductId());
				//client.prepareUpdate("17go", "product", product.getProductId());
				//添加request参数 json
				//request.setFetchSource(json).get();
				//request.setFetchSource(true).get();
				//request.doc(json).doc();
				//client.update(request);
			
			}
		}else{
				client.admin().indices().prepareCreate("17go").get();
				for (Product product : pList) {
					//先把product序列化成json
					String json = MapperUtil.MP.writeValueAsString(product);
					IndexRequestBuilder request = 
					client.prepareIndex("17go", "product", product.getProductId());
					//client.prepareUpdate("17go", "product", product.getProductId());
					//添加request参数 json
					//request.setFetchSource(json).get();
					request.setSource(json).get();
			}
		}
		
	}
	
	
	
	
	
}
