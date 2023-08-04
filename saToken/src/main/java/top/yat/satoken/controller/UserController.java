package top.yat.satoken.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XueYat
 * @date 2023/07/21
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {

    /**
     * 会话登录接口
     *
     * @param name name
     * @param pwd  pwd
     * @return SaResult
     */
    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        if ("admin".equals(name) && "admin".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("doLogin-登录成功");
        }
        if ("xue".equals(name) && "xue".equals(pwd)) {
            StpUtil.login(10002);
            return SaResult.ok("doLogin-登录成功");
        }
        return SaResult.error("doLogin-登录失败");
    }

    /**
     * 获取当前会话是否已经登录，返回true=已登录，false=未登录
     *
     * @param headers headers
     * @return SaResult
     */
    @RequestMapping("isLogin")
    public SaResult isLogin(@RequestHeader HttpHeaders headers) {
        String cookie = headers.getFirst("cookie");
        log.info("isLogin-cookie: {}", cookie);
        if (StpUtil.isLogin()) {
            // 获取当前会话账号id
            Object loginId = StpUtil.getLoginId();
            // 获取当前会话的 token 值
            String tokenValue = StpUtil.getTokenValue();
            // 获取当前`StpLogic`的 token 名称
            String tokenName = StpUtil.getTokenName();
            // 获取当前会话剩余有效期（单位：s，返回-1代表永久有效）
            long tokenTimeout = StpUtil.getTokenTimeout();
            // 获取当前会话的 token 信息参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 获取：当前账号所拥有的权限集合
            List<String> permissionList = StpUtil.getPermissionList();
            // 获取：当前账号所拥有的角色集合
            List<String> roleList = StpUtil.getRoleList();
            // 获取当前账号 id 的 Account-Session (必须是登录后才能调用)
            SaSession session = StpUtil.getSession();
            // 获取当前 Token 的 Token-Session 对象
            SaSession tokenSession = StpUtil.getTokenSession();
            Map res = new HashMap(16);
            res.put("loginId", loginId);
            res.put("tokenValue", tokenValue);
            res.put("tokenName", tokenName);
            res.put("tokenTimeout", tokenTimeout);
            res.put("tokenInfo", tokenInfo);
            res.put("permissionList", permissionList);
            res.put("roleList", roleList);
            res.put("session", session);
            res.put("tokenSession", tokenSession);
            return SaResult.data(res);
        }
        return SaResult.error("isLogin-未登录");
    }

    /**
     * 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
     *
     * @param headers headers
     * @return SaResult
     */
    @RequestMapping("checkLogin")
    public SaResult checkLogin(@RequestHeader HttpHeaders headers) {
        String cookie = headers.getFirst("cookie");
        log.info("checkLogin-cookie: {}", cookie);
        try {
            StpUtil.checkLogin();
            return SaResult.ok("checkLogin-已登录");
        } catch (Exception e) {
            return SaResult.error("checkLogin-未登录");
        }
    }

    /**
     * 当前会话注销登录
     *
     * @param headers headers
     * @return SaResult
     */
    @RequestMapping("logout")
    public SaResult logout(@RequestHeader HttpHeaders headers) {
        String cookie = headers.getFirst("cookie");
        log.info("logout-cookie: {}", cookie);
        StpUtil.logout();
        return SaResult.ok("logout-当前会话注销登录");
    }

}

