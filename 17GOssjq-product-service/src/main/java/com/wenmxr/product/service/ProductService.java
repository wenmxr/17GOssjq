package com.wenmxr.product.service;

import com.wenmxr.pojo.Product;
import com.wenmxr.product.mapper.ProductMapper;
import com.wenmxr.utils.MapperUtil;
import com.wenmxr.vo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: 17GOssjq
 * @description: 商品系统
 * @author: XuDeming
 * @date: 2020-03-13 21:56:37
 **/
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper = null;

    public EasyUIResult queryProductByPage(Integer page, Integer rows) {
        // 准备EasyUIResult对象
        EasyUIResult result = new EasyUIResult();
        // 查询商品总数
        int total = productMapper.queryTotalCount();
        // 封装商品总数
        result.setTotal(total);
        // start商品起始下标
        int start = (page - 1) * rows;
        // 查询商品 rows 查询条数
        List<Product> products = productMapper.queryProducts(start, rows);
        result.setRows(products);
        return result;
    }

    public Product queryOneProduct(String productId) {
        Jedis jd = new Jedis("47.99.106.114",6379);
        jd.auth("sun+moon");
        // 单个商品的缓存逻辑
        /*
         * 1.根据生成对应业务逻辑的key值
         * 2.判断key在redis是否存在
         * 		2.1不存在，实现持久层调用，将数据存放到redis一份，再将数据返回
         * 		2.2存在数据，解析数据返回，无需调用持久层
         */
        String productQueryKey = "product_query_" + productId;
        // 添加锁判断逻辑
        String productLock = "product_" + productId + ".lock";
        if (jd.exists(productLock)) {
            //该商品由于其它更优先的权限操作锁住 直接调用持久层
            return productMapper.selectProductById(productId);
        }
        try {
            if(jd.exists(productQueryKey)) {
                // 说明数据缓存命中直接解析返回
                // 获取数据
                String productJson = jd.get(productQueryKey);
                // 反序列化
                Product product = MapperUtil.MP.readValue(productJson, Product.class);
                return product;
            } else {// 说明缓存没有数据
                Product product = productMapper.selectProductById(productId);
                // 序列化解析成json
                String productJson = MapperUtil.MP.writeValueAsString(product);
                // 放入缓存redis 为了下次和后续调用可以访问缓存逻辑
                jd.setex(productQueryKey, 60 * 60 * 2, productJson);
                return product;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            jd.close();
        }
    }

    public void addProduct(Product product) {
        Jedis jd = new Jedis("47.99.106.114",6379);
        jd.auth("sun+moon");
        // product 对象没有productId uuid
        String productId = UUID.randomUUID().toString();
        String productKey = "product_query_" + productId;
        Timestamp productAddtime = new Timestamp(System.currentTimeMillis());
        product.setProductId(productId);
        product.setProductAddtime(productAddtime);
        // 商品新增缓存
        try {
            // 序列化解析成json
            String productJson = MapperUtil.MP.writeValueAsString(product);
            // 放入缓存redis 方便后续用户查询访问缓存逻辑
            jd.setex(productKey, 60 * 60 * 2, productJson);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            jd.close();
        }
        productMapper.insertProduct(product);
    }

    public void updateProduct(Product product) {
        Jedis jd = new Jedis("47.99.106.114",6379);
        jd.auth("sun+moon");
        // redis的内存锁解决更新和查询的高并发冲突
        // 先对当前执行更新的商品 做redis的查询锁
        String productLock = "product_" + product.getProductId() + ".lock";
        jd.set(productLock, "");
        // 保证更新的数据，缓存和数据库的一致 将缓存数据删除
        jd.del("product_query_" + product.getProductId());
        // 更新数据库商品数据
        productMapper.updateProductById(product);
        // 释放锁
        jd.del(productLock);
        jd.close();
    }
}
