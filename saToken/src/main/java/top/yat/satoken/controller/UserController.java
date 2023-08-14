package top.yat.satoken.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yat.common.base.Result;
import top.yat.satoken.pojo.entity.PageQuery;
import top.yat.satoken.pojo.entity.User;
import top.yat.satoken.service.IUserService;

import javax.annotation.Resource;

import static top.yat.common.utils.ResultUtil.success;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @SaCheckPermission("system:user:list")
    @GetMapping("/list")
    public Result list(User user, PageQuery pageQuery) {
        return success(userService.list(user, pageQuery));
    }
}
