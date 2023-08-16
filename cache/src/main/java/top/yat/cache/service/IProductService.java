package top.yat.cache.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.cache.pojo.PageQuery;
import top.yat.cache.pojo.Product;

public interface IProductService extends IService<Product> {

    Page<Product> getProductList(Product product, PageQuery pageQuery);

    Product getProductById(Integer produceId);

    void updateProductById(Product product);

    void delProductById(Integer produceId);
}
