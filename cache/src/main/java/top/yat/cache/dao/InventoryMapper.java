package top.yat.cache.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import top.yat.cache.pojo.entity.Inventory;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.pojo.vo.ProductVO;

@Component
public interface InventoryMapper extends BaseMapper<Inventory> {

    Page<ProductVO> getProductInventoryList(Product product, Page page);

    void updateProductInventoryById(Inventory inventory);

}
