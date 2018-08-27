package com.ccsu.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hangs.zhang
 * @date 2018/8/27
 * *********************
 * function:
 */
@Component
@ConfigurationProperties(prefix = "my-app")
@Data
public class AppConfig {

    private String name;

    private String token;

}
