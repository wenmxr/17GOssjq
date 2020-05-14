package com.wenmxr.cart.service;

import com.wenmxr.cart.mapper.CartMapper;
import com.wenmxr.pojo.Cart;
import com.wenmxr.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	RestTemplate client;

	public List<Cart> queryMyCart(String userId) {
		return cartMapper.selectCartsByUserId(userId);
	}

	public void addMyCart(Cart cart) {
		/*
		 * 1.判断存在 select * from t_cart where user_id and product_id
		 *   如果存在 更新num 不存在 表示新增访问商品服务补齐数据
		 */
		Cart exist = cartMapper.selectCartByUserIdAddProductId(cart);
		if (exist != null) { // 存在则更新数据 
			// update t_cart set num=#{num} user_id=#{userId} and product_id=#{productId}
			exist.setNum(exist.getNum() + cart.getNum());
			cartMapper.updateNumByUserIdAddProductId(exist);
		} else {// insert
			// 从商品系统中Product 获取productId 
			Product product = client.getForObject("http://productservice" + "/product/manage/item/" + cart.getProductId(), Product.class);
			cart.setProductPrice(product.getProductPrice());
			cart.setProductName(product.getProductName());
			cart.setProductImg1url(product.getProductImg1url());
			cartMapper.insertCart(cart);
		}
	}

	public void updateCartNum(Cart cart) {
		cartMapper.updateNumByUserIdAddProductId(cart);
	}

	public void deleteCart(Cart cart) {
		cartMapper.deleteCartByUserIdAndProductId(cart);
	}

}
