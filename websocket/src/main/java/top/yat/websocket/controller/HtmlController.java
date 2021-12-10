package top.yat.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author YatXue
 * @date 2021/12/10 13:53
 */
@Controller
public class HtmlController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login() {
        return "/home";
    }
}
