package com.ccsu.client.listener;

import com.ccsu.client.service.HotConfigOnFieldManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @author hangs.zhang
 * @date 2018/8/13
 * *********************
 * function:
 */
@Slf4j
@Component
public class ZConfigOnFieldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private HotConfigOnFieldManager hotConfigManager;

    @Autowired
    public ZConfigOnFieldApplicationListener(HotConfigOnFieldManager hotConfigManager) {
        this.hotConfigManager = hotConfigManager;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            log.info("=====ContextRefreshedEvent====={}", contextRefreshedEvent.getSource().getClass().getName());
        }
    }
}
