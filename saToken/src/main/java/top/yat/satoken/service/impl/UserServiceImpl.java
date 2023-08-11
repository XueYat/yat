package top.yat.satoken.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yat.common.utils.StringUtils;
import top.yat.satoken.constant.UserConstants;
import top.yat.satoken.entity.PageQuery;
import top.yat.satoken.entity.User;
import top.yat.satoken.mapper.UserMapper;
import top.yat.satoken.service.IUserService;

import javax.annotation.Resource;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> list(User user, PageQuery pageQuery) {
        return userMapper.list(pageQuery.build(), buildQueryWrapper(user));
    }

    private Wrapper<User> buildQueryWrapper(User user) {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq(StringUtils.isNotEmpty(user.getDelFlag()), "del_flag", UserConstants.USER_NORMAL)
                .eq(ObjectUtil.isNotNull(user.getUserId()), "user_id", user.getUserId())
                .like(StringUtils.isNotBlank(user.getUsername()), "username", user.getUsername())
                .eq(ObjectUtil.isNotNull(user.getStatus()), "status", user.getStatus())
                .like(StringUtils.isNotBlank(user.getMobile()), "mobile", user.getMobile());
        return wrapper;
    }

}
