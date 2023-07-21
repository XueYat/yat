package top.yat.satoken.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XueYat
 * @date 2023/07/21
 */
@Slf4j
@RestController
@RequestMapping("/biz/")
public class BizController {

    /**
     * 登录校验：只有登录之后才能进入该方法
     *
     * @return String
     */
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    /**
     * 角色校验：必须具有指定角色才能进入该方法
     *
     * @return String
     */
    @SaCheckRole("super-admin")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

    /**
     * 权限校验：必须具有指定权限才能进入该方法
     *
     * @return String
     */
    @SaCheckPermission("user.add")
    @RequestMapping("add1")
    public String add1() {
        return "用户增加";
    }

    /**
     * 二级认证校验：必须二级认证之后才能进入该方法
     *
     * @return String
     */
    @SaCheckSafe()
    @RequestMapping("add2")
    public String add2() {
        return "用户增加";
    }

    /**
     * Http Basic 校验：只有通过 Basic 认证后才能进入该方法
     *
     * @return String
     */
    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("add3")
    public String add3() {
        return "用户增加";
    }

    /**
     * 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
     *
     * @return String
     */
    @SaCheckDisable("comment")
    @RequestMapping("send")
    public String send() {
        return "查询用户信息";
    }

    /**
     * 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
     *
     * @return SaResult
     */
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

}
