package com.ccsu.client.service;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2018/8/27
 * *********************
 * function:
 */
public interface HotConfigOnFieldManager {

    void setConfig(String key, Map<String, String> map);

    void getConfig(String key);

    Map<String, String> getConfigFromRemote(String key);

    void updateAll();

    void updateByKey(String key);

}
