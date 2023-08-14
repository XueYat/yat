package top.yat.satoken.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.satoken.pojo.entity.PageQuery;
import top.yat.satoken.pojo.entity.User;


public interface IUserService extends IService<User> {

    Page<User> list(User user, PageQuery pageQuery);
}
