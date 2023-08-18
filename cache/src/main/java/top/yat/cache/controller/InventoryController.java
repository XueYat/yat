package top.yat.cache.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.yat.cache.pojo.entity.Inventory;
import top.yat.cache.pojo.entity.Product;
import top.yat.cache.pojo.page.PageQuery;
import top.yat.cache.service.IInventoryService;
import top.yat.common.annotation.RedisLimit;
import top.yat.common.base.Result;
import top.yat.common.utils.ResultUtil;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    private IInventoryService inventoryService;

    @GetMapping("/getProductInventoryList")
    public Result getProductInventoryList(Product product, PageQuery pageQuery) {
        return ResultUtil.success(inventoryService.getProductInventoryList(product, pageQuery));
    }

    @RedisLimit(permitsPerSecond = 2, expire = 1)
    @PostMapping("/updateProductInventoryById")
    public Result updateProductInventoryById(@RequestBody Inventory inventory) {
        inventoryService.updateProductInventoryById(inventory);
        return ResultUtil.success();
    }

}
