package top.yat.stream.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yat.common.base.Result;
import top.yat.common.utils.ResultUtil;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author XueYat
 * @date 2023/04/25
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    public Result hello() {
        String str = "hello";
        return ResultUtil.success(str);
    }

    public static void main(String[] args) {
        // Collectors实现了很多规约操作，例如将流转换成集合和聚合元素，可用于返回列表或字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        log.info("筛选列表：{}", JSON.toJSONString(filtered));
        String collect = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("、 "));
        log.info("合并字符串：{}", JSON.toJSONString(collect));

        // limit用于获取指定数量的流
        // forEach来迭代流中的每个数据
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        // map用于映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        log.info("map去重：{}", JSON.toJSONString(squaresList));

        // filter用于通过设置的条件过滤出元素
        long count = strings.stream().filter(string -> !string.isEmpty()).count();
        log.info("filter过滤：{}", count);

        // sorted用于对流进行排序
        random.ints().limit(10).sorted().forEach(System.out::println);

        // parallelStream是流并行处理程序的代替方法
        long count1 = strings.parallelStream().filter(String::isEmpty).count();
        log.info("parallelStream过滤：{}", count1);

        // 统计
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        log.info("列表中最大的数：{}", stats.getMax());
        log.info("列表中最小的数：{}", stats.getMin());
        log.info("列表中数之和：{}", stats.getSum());
        log.info("列表中平均数：{}", stats.getAverage());

    }

}
