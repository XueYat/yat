package top.yat.cpuhigh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yat.common.base.Result;
import top.yat.common.utils.ResultUtil;

/**
 * @author XueYat
 * @date 2023/04/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    public Result hello() {
        String str = "hello";
        return ResultUtil.success(str);
    }

    @PostMapping("/cpu")
    public Result cpu() {
        while (true) {

        }
    }

}
