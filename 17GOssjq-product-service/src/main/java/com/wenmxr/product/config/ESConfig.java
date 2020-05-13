package com.wenmxr.product.config;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.List;

/**
 * 通过属性读取,封装一个es连接客户端
 * TransportClient提供给系统任意位置
 * 注入使用
 * @author tedu
 *
 */
@Configuration
@ConfigurationProperties(prefix="17go.es")
public class ESConfig {
	/*
	//集群名称
	private String clusterName;
	*/
	//节点连接node信息
	private List<String> nodes;
	//初始化方法对象
	@Bean
	public TransportClient initTransportClient(){
		try{
			/*
			//Setting对象,包装clusterName
			Settings set=Settings.builder()
					.put("cluster.name",clusterName)
					.build();
			TransportClient client=new PreBuiltTransportClient(set);
			*/
			TransportClient client=new
					PreBuiltTransportClient(Settings.EMPTY);
			//为client赋值 底层使用的prepare预包装,提供ip 和端口
			for(String node:nodes){
				//10.9.100.26:9300
				String host=node.split(":")[0];
				int port=Integer.parseInt(node.split(":")[1]);
				InetSocketTransportAddress ista=
						new InetSocketTransportAddress(
							InetAddress.getByName(host), 
								port);
				client.addTransportAddress(ista);
			}
			return client;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	/*
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	*/
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
}
