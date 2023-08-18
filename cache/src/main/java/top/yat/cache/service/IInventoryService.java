package top.yat.cache.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.cache.pojo.entity.Inventory;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.pojo.page.PageQuery;
import top.yat.cache.pojo.vo.ProductVO;

public interface IInventoryService extends IService<Inventory> {

    Page<ProductVO> getProductInventoryList(Product product, PageQuery pageQuery);

    void updateProductInventoryById(Inventory inventory);

}
