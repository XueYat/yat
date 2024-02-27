package top.yat.designMode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import top.yat.common.base.Result;
import top.yat.designMode.request.TransferFeeRequest;
import top.yat.designMode.service.Execute;
import top.yat.designMode.service.LogisticsService;
import top.yat.designMode.service.impl.CacheExecute;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static top.yat.common.utils.ResultUtil.success;

@Slf4j
@RestController
@RequestMapping("/design")
public class DesignModeController {

    @Resource
    private List<LogisticsService> logisticsServices;

    @Resource
    @Qualifier("defaultExecute")
    private Execute defaultExecute;

    @Resource
    @Qualifier("cacheExecute")
    private Execute cahceExecute;

    /**
     * 策略模式
     * 策略模式（Strategy Pattern）属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。
     * 其主要目的是通过定义相似的算法，替换if else 语句写法，并且可以随时相互替换。
     */
    @PostMapping("/strategy")
    public Result strategy(@RequestBody TransferFeeRequest transferFeeRequest) {
        LogisticsService logisticsService = this.logisticsServices.stream()
                .filter(l -> l.isCurrentLogistics(transferFeeRequest.getType()))
                .findFirst().orElse(null);
        return Objects.nonNull(logisticsService) ? success(logisticsService.calculateFee(transferFeeRequest)) : success();
    }

    /**
     * 装饰器模式
     * 装饰器模式（Decorator Pattern） 也称为包装模式(Wrapper Pattern) 是指在不改变原有对象的基础之上，将功能附加到对象上，提供了比继承更有弹性的替代方案(扩展原有对象的功能)，属于结构型模式。
     * 装饰器模式的核心是功能扩展，使用装饰器模式可以透明且动态地扩展类的功能。
     */
    @GetMapping("/decorator")
    public Result decorator() {
        Execute execute = new CacheExecute(defaultExecute);
        JSONObject jsonObject = (JSONObject) execute.query("test");
        log.info("结果数据为: {}", JSON.toJSONString(jsonObject));
        JSONObject jsonObject1 = (JSONObject) execute.query("test");
        log.info("第二次结果数据为: {}", JSON.toJSONString(jsonObject1));
        return success();
    }

}
