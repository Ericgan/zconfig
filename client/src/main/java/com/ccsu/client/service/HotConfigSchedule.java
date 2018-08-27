package com.ccsu.client.service;

/**
 * @author hangs.zhang
 * @date 2018/8/27
 * *********************
 * function:
 * 1、一分钟一次，主动从server拉取数据 pull
 * 2、被server推送数据 pushed
 */
public interface HotConfigSchedule {

    /**
     * 主动pull data
     */
    void pullData();

    /**
     * 被动push data
     */
    void pushedData();

}
