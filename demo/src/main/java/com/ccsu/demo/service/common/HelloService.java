package com.ccsu.demo.service.common;

import com.ccsu.client.anno.ZConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2018/8/27
 * *********************
 * function:
 */
@Slf4j
@Service
public class HelloService {

    @ZConfig("hello")
    private Map<String, String> map;

    @PostConstruct
    public void init() {
        printMap();
    }

    public void printMap() {
        map.forEach((k, v) -> log.info("{}:{}", k, v));
    }

}
