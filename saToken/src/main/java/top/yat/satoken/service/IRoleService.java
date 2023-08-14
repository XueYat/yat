package top.yat.satoken.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.satoken.pojo.entity.Role;

import java.util.Set;

public interface IRoleService extends IService<Role> {

    Set<String> getRolePermission(Integer userId);
}
