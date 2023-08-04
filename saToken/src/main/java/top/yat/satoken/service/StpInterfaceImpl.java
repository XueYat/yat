package top.yat.satoken.service;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义权限加载接口实现类
 *
 * @author XueYat
 * @date 2023/07/21
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        if ("10001".equals(loginId.toString())) {
            list.add("*");
        } else {
            list.add("user.get");
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        if ("10001".equals(loginId.toString())) {
            list.add("admin");
            list.add("super-admin");
        } else {
            list.add("user");
        }
        return list;
    }

}
