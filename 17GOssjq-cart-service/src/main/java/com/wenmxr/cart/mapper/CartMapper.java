package com.wenmxr.cart.mapper;

import com.wenmxr.pojo.Cart;

import java.util.List;

public interface CartMapper {

	List<Cart> selectCartsByUserId(String userId);

	Cart selectCartByUserIdAddProductId(Cart cart);

	void updateNumByUserIdAddProductId(Cart exist);

	void insertCart(Cart cart);

	void deleteCartByUserIdAndProductId(Cart cart);

}
