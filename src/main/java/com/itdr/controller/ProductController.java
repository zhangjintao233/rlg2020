package com.itdr.controller;


import com.itdr.common.ServerResponse;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;
import com.itdr.service.CategoryService;
import com.itdr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequestMapping("/portal/product/")
public class ProductController {


    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * 获取商品直接分类
     * @param pid
     * @return
     */
    @RequestMapping("fen.do")
    public ServerResponse<CategoryService> fen(Integer pid){
        return categoryService.fen(pid);
    }

    /**
     * 根据id获取商品商品
     * @param productId
     * @return
     */
    @RequestMapping("selectid.do")
    public ServerResponse<ProductVo> selectProductById(Integer productId){
        return productService.selectProductById(productId);
    }

    /**                                                word       模糊字
     * 商品模糊查询                                     pageNum    每页显示的条数
     * @param word                                     pageSize   每页的大小
     * @return                                         orderBy    排序规则
     */
    @RequestMapping("mohu.do")
    public ServerResponse<ProductVo> selectMoHu(String word,
                                                @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize,
                                                @RequestParam(value = "orderBy",required = false,defaultValue = "") String orderBy){
        return productService.selectProductByName(word,pageNum,pageSize,orderBy);
    }
}
