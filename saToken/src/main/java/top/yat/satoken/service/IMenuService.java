package top.yat.satoken.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.satoken.entity.Menu;

import java.util.Set;

public interface IMenuService extends IService<Menu> {

    Set<String> getMenuPermission(Integer menuId);
}
