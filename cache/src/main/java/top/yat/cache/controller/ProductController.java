package top.yat.cache.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.yat.cache.pojo.page.PageQuery;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.service.IProductService;
import top.yat.common.base.Result;
import top.yat.common.utils.ResultUtil;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    IProductService productService;

    @GetMapping("/getProductList")
    public Result getProductList(Product product, PageQuery pageQuery) {
        log.info("getProductList");
        return ResultUtil.success(productService.getProductList(product, pageQuery));
    }

    @GetMapping("/getProductById/{productId}")
    public Result getProductById(@PathVariable("productId") Integer productId) {
        return ResultUtil.success(productService.getProductById(productId));
    }

    @PostMapping("/updateProductById")
    public Result updateProductById(@RequestBody Product product) {
        productService.updateProductById(product);
        return ResultUtil.success();
    }

    @GetMapping("/delProductById/{productId}")
    public Result delProductById(@PathVariable("productId") Integer productId) {
        productService.delProductById(productId);
        return ResultUtil.success();
    }

}
