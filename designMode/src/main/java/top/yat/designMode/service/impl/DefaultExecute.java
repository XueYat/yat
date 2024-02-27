package top.yat.designMode.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yat.designMode.service.Execute;

@Slf4j
@Service("defaultExecute")
public class DefaultExecute implements Execute {
    @Override
    public Object query(String id) {
        log.info("执行查询语句");

        // 模拟假数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "test");
        return jsonObject;
    }

    @Override
    public void insert() {
        log.info("执行插入语句");
    }

}
