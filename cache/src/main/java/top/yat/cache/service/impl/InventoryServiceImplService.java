package top.yat.cache.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yat.cache.dao.InventoryMapper;
import top.yat.cache.pojo.entity.Inventory;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.pojo.page.PageQuery;
import top.yat.cache.pojo.vo.ProductVO;
import top.yat.cache.service.IInventoryService;

import javax.annotation.Resource;

@Slf4j
@Service
public class InventoryServiceImplService extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    private Wrapper<Inventory> buildQueryWrapper(Inventory inventory) {
        QueryWrapper<Inventory> wrapper = Wrappers.query();
        wrapper.eq(ObjectUtil.isNotNull(inventory.getProductId()), "product_id", inventory.getProductId());
        return wrapper;
    }

    private Wrapper<Inventory> buildUpdateWrapper(Inventory inventory) {
        UpdateWrapper<Inventory> wrapper = new UpdateWrapper<>();
        wrapper.set("num", inventory.getNum());
        wrapper.eq("product_id", inventory.getProductId())
                .eq("version", inventory.getVersion());
        return wrapper;
    }

    @Override
    public Page<ProductVO> getProductInventoryList(Product product, PageQuery pageQuery) {
        return inventoryMapper.getProductInventoryList(product, pageQuery.build());
    }

    @Override
    public void updateProductInventoryById(Inventory inventory) {
        Inventory i = inventoryMapper.selectOne(buildQueryWrapper(inventory));
        inventory.setNum(i.getNum() - 1);
        // 乐观锁实现
        inventory.setVersion(i.getVersion());
        //inventoryMapper.updateProductInventoryById(inventory);
        // 乐观锁仅支持 updateById(id) 与 update(entity, wrapper) 方法
        inventoryMapper.update(inventory, buildUpdateWrapper(inventory));
    }

}
