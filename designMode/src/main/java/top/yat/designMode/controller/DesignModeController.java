package top.yat.designMode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import top.yat.common.base.Result;
import top.yat.designMode.abstracts.Game;
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

    @Resource
    @Qualifier("game1")
    private Game game1;

    @Resource
    @Qualifier("game2")
    private Game game2;

    /**
     * 策略模式
     * 策略模式（Strategy Pattern）属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。
     * 其主要目的是通过定义相似的算法，替换if else 语句写法，并且可以随时相互替换。
     * 优点：扩展性好，可以在不修改对象结构的情况下，为新的算法进行添加新的类进行实现；灵活性好，可以对算法进行自由切换；
     * 缺点：使用策略类变多，会增加系统的复杂度；客户端必须知道所有的策略类才能进行调用；
     * 使用场景：如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为；一个系统需要动态地在几种算法中选择一种;如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现;
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
     * 优点：装饰器是继承的有力补充，比继承灵活，在不改变原有对象的情况下，动态的给一个对象扩展功能，即插即用；通过使用不用装饰类及这些装饰类的排列组合，可以实现不同效果；装饰器模式完全遵守开闭原则
     * 缺点：会增加许多子类，过度使用会增加程序得复杂性
     * 使用场景：当需要给一个对象动态的添加一些行为时，在不改变原有对象的基础上，可以使用装饰器模式给一个对象动态的添加一些功能；
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

    /**
     * 模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。
     * 优点：扩展性好，对不变的代码进行封装，对可变的进行扩展；可维护性好，因为将公共代码进行了提取，使用的时候直接调用即可；
     * 缺点：在使用模板方法模式时，每个子类中都有一个实现方法，如果需要扩展类的行为，那么就需要在每一个子类中都增加实现方法。这样会增加代码量，也增加了类的依赖关系。
     * 使用场景：有多个子类共有逻辑相同的方法；重要的、复杂的方法，可以考虑作为模板方法。
     */
    @GetMapping("/template")
    public Result template() {
        game1.play();
        game2.play();
        return success();
    }

}
