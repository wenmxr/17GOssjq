package com.wenmxr.product.mapper;

import java.util.List;

import com.wenmxr.pojo.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

	int queryTotalCount();

	// 由于抽象方法具有两个以上参数 映射文件xml使用参数时无法使用变量名称
	List<Product> queryProducts(@Param("start") int start, @Param("rows") Integer rows);

	Product selectProductById(String productId);

	void insertProduct(Product product);

	void updateProductById(Product product);

}
