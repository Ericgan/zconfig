package com.ccsu.client.service.impl;

import com.ccsu.client.config.AppConfig;
import com.ccsu.client.service.HotConfigOnFieldManager;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hangs.zhang
 * @date 2018/8/27
 * *********************
 * function:
 */
@Slf4j
@Service
public class HotConfigOnFieldManagerImpl implements HotConfigOnFieldManager {

    private AppConfig appConfig;

    private Map<String, Map<String, String>> configMap = new ConcurrentHashMap<>();

    @Autowired
    public HotConfigOnFieldManagerImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public void setConfig(String key, Map<String, String> map) {
        configMap.put(key, map);
    }

    @Override
    public void getConfig(String key) {
        configMap.get(key);
    }

    @Override
    public Map<String, String> getConfigFromRemote(String key) {
        log.info("app name : {}     app token : {}", appConfig.getName(), appConfig.getToken());
        // TODO: 2018/8/27 请求server，更新config
        HashMap<String, String> map = Maps.newHashMap();
        map.put("name", "zhanghang");
        return map;
    }

    @Override
    public void updateAll() {
        log.info("Pull Hot Config");
        configMap.forEach((k, v) -> updateByKey(k));
    }

    @Override
    public void updateByKey(String key) {
        Map<String, String> map = getConfigFromRemote(key);
        setConfig(key, map);
    }

}
