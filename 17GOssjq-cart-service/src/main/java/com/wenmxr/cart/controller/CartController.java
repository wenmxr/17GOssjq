package com.wenmxr.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenmxr.cart.service.CartService;
import com.wenmxr.pojo.Cart;
import com.wenmxr.vo.SysResult;

@RestController
@RequestMapping("/cart/manage")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 查询购物车
	 * @param userId
	 * @return
	 */
	@RequestMapping("/query")
	public List<Cart> queryMyCart(String userId) {
		return cartService.queryMyCart(userId);
	}
	
	/**
	 * 新增购物车
	 * @param cart
	 * @return
	 */
	@RequestMapping("/save")
	public SysResult addMyCart(Cart cart) {
		try {
			cartService.addMyCart(cart);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "新增失败", null);
		}
	}
	
	/**
	 * 更新num
	 * @param cart
	 * @return
	 */
	@RequestMapping("/update")
	public SysResult updateCartNum(Cart cart) {
		try {
			cartService.updateCartNum(cart);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "更新失败", null);
		}
	}
	
	@RequestMapping("/delete")
	public SysResult deleteCart(Cart cart) {
		try {
			cartService.deleteCart(cart);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "删除失败", null);
		}
	}
}
