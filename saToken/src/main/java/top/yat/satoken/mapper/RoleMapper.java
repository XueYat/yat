package top.yat.satoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.yat.satoken.entity.Role;

import java.util.List;

@Component
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRolePermissionByUserId(Integer userId);
}
