package top.yat.designMode.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import top.yat.designMode.service.Execute;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("cacheExecute")
public class CacheExecute implements Execute {
    // 传的是DefaultExecute
    private final Execute delegate;
    private final Map<String, Object> caches = new HashMap<>();

    public CacheExecute(@Qualifier("defaultExecute") Execute delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object query(String id) {
        // 从缓存中取出数据
        if (caches.containsKey(id)) {
            log.info("从缓存中取出数据");
            return caches.get(id);
        }
        Object data = delegate.query(id);
        caches.put(id, data);
        log.info("存储数据到缓存中");
        return data;
    }

    @Override
    public void insert() {
        delegate.insert();
    }
}
