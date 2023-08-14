package top.yat.satoken.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.yat.satoken.pojo.entity.User;


@Component
public interface UserMapper extends BaseMapper<User> {
    Page<User> list(@Param("page") Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> userWrapper);
}
