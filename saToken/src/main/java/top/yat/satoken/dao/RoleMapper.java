package top.yat.satoken.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.pojo.entity.Role;

import java.util.List;

@Component
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRolePermissionByUserId(Integer userId);
}
