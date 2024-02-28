package top.yat.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yat.common.base.Result;
import top.yat.websocket.service.WebSocketService;

import javax.annotation.Resource;

import static top.yat.common.utils.ResultUtil.success;

@Slf4j
@RestController
@RequestMapping("/send")
public class SendController {

    @Resource
    private WebSocketService webSocketService;

    @GetMapping("/send/{userId}")
    public Result send(@PathVariable("userId") Long userId) {
        webSocketService.sendToOne("Hello", userId);
        return success();
    }

}
