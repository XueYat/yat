package top.yat.cache.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.yat.cache.dao.ProductMapper;
import top.yat.cache.pojo.page.PageQuery;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.service.IProductService;
import top.yat.common.utils.StringUtils;

import javax.annotation.Resource;

import static top.yat.common.constant.ErrorCodeConstants.PRODUCT_IS_NOT_EXISTS;
import static top.yat.common.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@Service
@CacheConfig(cacheNames = {"product"})
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    private Wrapper<Product> buildQueryWrapper(Product product) {
        QueryWrapper<Product> wrapper = Wrappers.query();
        wrapper.eq(StringUtils.isNotEmpty(product.getDelFlag()), "del_flag", 1)
                .eq(ObjectUtil.isNotNull(product.getProductId()), "product_id", product.getProductId())
                .like(StringUtils.isNotBlank(product.getName()), "name", product.getName())
                .eq(ObjectUtil.isNotNull(product.getStatus()), "status", product.getStatus());
        return wrapper;
    }

    @Override
    public Page<Product> getProductList(Product product, PageQuery pageQuery) {
        return productMapper.selectPage(pageQuery.build(), buildQueryWrapper(product));
    }

    @Override
    @Cacheable(key="#produceId")
    public Product getProductById(Integer produceId) {
        QueryWrapper<Product> wrapper = Wrappers.query();
        wrapper.eq("product_id", produceId);
        Product product = productMapper.selectOne(wrapper);
        log.info("getProductById: {}", JSON.toJSONString(product));
        if (ObjectUtil.isNull(product)) {
            throw exception(PRODUCT_IS_NOT_EXISTS);
        }
        return productMapper.selectOne(wrapper);
    }

    @Override
    @CachePut(key = "#product.productId")
    public void updateProductById(Product product) {
        productMapper.updateById(product);
    }

    @Override
    @CacheEvict(key = "#produceId")
    public void delProductById(Integer produceId) {
        productMapper.deleteById(produceId);
    }

}
