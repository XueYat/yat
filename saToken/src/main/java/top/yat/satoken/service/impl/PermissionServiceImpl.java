package top.yat.satoken.service.impl;

import org.springframework.stereotype.Service;
import top.yat.satoken.service.IMenuService;
import top.yat.satoken.service.IPermissionService;
import top.yat.satoken.service.IRoleService;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private IRoleService IRoleService;

    @Resource
    private IMenuService IMenuService;

    @Override
    public Set<String> getRolePermission(Integer userId) {
        return IRoleService.getRolePermission(userId);
    }

    @Override
    public Set<String> getMenuPermission(Integer userId) {
        return IMenuService.getMenuPermission(userId);
    }
}
