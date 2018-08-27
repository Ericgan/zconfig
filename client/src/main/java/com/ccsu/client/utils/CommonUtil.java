package com.ccsu.client.utils;

/**
 * @author hangs.zhang
 * @date 2018/8/14
 * *********************
 * function:
 */
public class CommonUtil {

    public static String getRealKey(String beanName, String value) {
        return beanName + "-" + value;
    }

}
