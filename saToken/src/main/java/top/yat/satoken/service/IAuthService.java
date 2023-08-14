package top.yat.satoken.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yat.satoken.pojo.vo.AuthVo;
import top.yat.satoken.pojo.entity.User;
import top.yat.satoken.pojo.vo.UserVo;

public interface IAuthService extends IService<User> {

    AuthVo doLogin(User user);

    UserVo getUserInfo();
}
