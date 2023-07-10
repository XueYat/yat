package top.yat.advice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.yat.advice.service.WhiteService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XueYat
 * @date 2023/07/07
 */
@Service
@Slf4j
public class WhiteServiceImpl implements WhiteService {

    @Cacheable(value = "whiteList")
    @Override
    public Set whiteList() {
        log.info("白名单缓存");
        Set set = new HashSet();
        set.add("/test/postQuery");
        return set;
    }
}
