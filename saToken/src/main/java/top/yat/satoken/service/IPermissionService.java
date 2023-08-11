package top.yat.satoken.service;

import java.util.Set;

public interface IPermissionService {

    Set<String> getRolePermission(Integer userId);

    Set<String> getMenuPermission(Integer userId);

}
