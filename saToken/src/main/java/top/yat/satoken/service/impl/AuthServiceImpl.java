package top.yat.satoken.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yat.satoken.constant.UserConstants;
import top.yat.satoken.pojo.vo.AuthVo;
import top.yat.satoken.pojo.entity.User;
import top.yat.satoken.dao.AuthMapper;
import top.yat.satoken.pojo.vo.UserVo;
import top.yat.satoken.service.IAuthService;

import javax.annotation.Resource;

import java.util.List;

import static top.yat.satoken.constant.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static top.yat.satoken.constant.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;
import static top.yat.satoken.exception.util.ServiceExceptionUtil.exception;

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, User> implements IAuthService {

    @Resource
    private AuthMapper authMapper;

    @Override
    public AuthVo doLogin(User user) {
        User u = authMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername())
                .eq("del_flag", UserConstants.USER_NORMAL));
        if (ObjectUtil.isNull(u)){
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if(!u.getPassword().equals(user.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (UserConstants.USER_DISABLE.equals(u.getStatus())) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        StpUtil.login(u.getUserId());
        String tokenValue = StpUtil.getTokenValue();
        AuthVo authVo = new AuthVo();
        authVo.setUserId(u.getUserId());
        authVo.setAccessToken(tokenValue);
        return authVo;
    }

    @Override
    public UserVo getUserInfo() {
        UserVo userVo = new UserVo();
        Integer userId = Integer.valueOf(StpUtil.getLoginId().toString());
        User user = authMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
        BeanUtil.copyProperties(user, userVo);
        List<String> permissionList = StpUtil.getPermissionList();
        List<String> roleList = StpUtil.getRoleList();
        userVo.setMenus(permissionList);
        userVo.setMenus(roleList);
        return userVo;
    }
}
