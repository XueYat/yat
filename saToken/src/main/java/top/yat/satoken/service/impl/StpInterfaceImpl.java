package top.yat.satoken.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;
import top.yat.satoken.service.IPermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private IPermissionService IPermissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        if ("1".equals(loginId.toString())) {
            list.add("*");
        } else {
            Set<String> menuPermission = IPermissionService.getMenuPermission(Integer.valueOf(loginId.toString()));
            list.addAll(menuPermission);
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        if ("1".equals(loginId.toString())) {
            list.add("super-admin");
        } else {
            Set<String> rolePermission = IPermissionService.getRolePermission(Integer.valueOf(loginId.toString())
            );
            list.addAll(rolePermission);
        }
        return list;
    }

}
