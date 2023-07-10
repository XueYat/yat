package top.yat.advice.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.yat.advice.pojo.User;

/**
 * @author XueYat
 * @date 2023/07/07
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @PostMapping("/select")
    public Object select(@RequestBody User user) {
        log.info("user: {}", JSON.toJSONString(user));
        return user;
    }

    @GetMapping("/query")
    public Object query(@RequestBody User user) {
        log.info("user: {}", JSON.toJSONString(user));
        return user;
    }

    @RequestMapping(value = "/getQuery", method = RequestMethod.GET)
    public Object getQuery(@RequestBody User user) {
        log.info("user: {}", JSON.toJSONString(user));
        return user;
    }

    @RequestMapping(value = "/postQuery", method = RequestMethod.POST)
    public Object postQuery(@RequestBody User user) {
        log.info("user: {}", JSON.toJSONString(user));
        return user;
    }

}
