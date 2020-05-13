package com.wenmxr.product.controller;

import com.wenmxr.pojo.Product;
import com.wenmxr.product.service.ProductService;
import com.wenmxr.vo.EasyUIResult;
import com.wenmxr.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: 17GOssjq
 * @description: 商品系统
 * @author: XuDeming
 * @date: 2020-03-13 21:53:11
 **/

@RestController
@RequestMapping("/product/manage")
public class ProductController {
    @Autowired
    private ProductService productService = null;

    /**
     * 商品分页查询
     * @param page 页数
     * @param rows 一页条数
     * @return
     */
    @RequestMapping("/pageManage")
    public EasyUIResult queryProductByPage(Integer page, Integer rows) {
        return productService.queryProductByPage(page, rows);
    }

    /**
     * 商品单个查询
     * @param productId 商品Id
     * @return
     */
    @RequestMapping("/item/{productId}")
    public Product queuryOneProduct(@PathVariable String productId) {
        System.out.println(productId);
        return productService.queryOneProduct(productId);
    }

    /**
     * 商品的新增
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public SysResult addProduct(Product product) {
        // 新增考虑成功和失败
        try {
            System.out.println(product);
            productService.addProduct(product);
            return SysResult.ok();// return new SysResult(200, "ok", null);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, e.getMessage(), null);
        }
    }

    /**
     * 商品的更新
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public SysResult updateProduct(Product product) {
        try {
            System.out.println(product);
            productService.updateProduct(product);
            return SysResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, e.getMessage(), null);
        }
    }
}
