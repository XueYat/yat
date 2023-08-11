package top.yat.satoken.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.satoken.controller.vo.UserVo;
import top.yat.satoken.entity.User;

public interface IAuthService extends IService<User> {

    UserVo doLogin(User user);
}
