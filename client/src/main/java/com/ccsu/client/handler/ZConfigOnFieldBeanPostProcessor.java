package com.ccsu.client.handler;

import com.ccsu.client.anno.ZConfig;
import com.ccsu.client.service.HotConfigOnFieldManager;
import com.ccsu.client.utils.CommonUtil;
import com.ccsu.client.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hangs.zhang
 * @date 2018/8/14
 * *********************
 * function:
 */
@SuppressWarnings("all")
@Component
@Slf4j
public class ZConfigOnFieldBeanPostProcessor implements BeanPostProcessor {

    private HotConfigOnFieldManager hotConfigManager;

    @Autowired
    public ZConfigOnFieldBeanPostProcessor(HotConfigOnFieldManager hotConfigManager) {
        this.hotConfigManager = hotConfigManager;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessBeforeInitialization, beanName:{}", beanName);
        List<Field> fields = ReflectionUtil.getFieldsWithAnnotation(bean, ZConfig.class);
        if (fields != null) {
            for (Field field : fields) {
                ZConfig zConfig = field.getAnnotation(ZConfig.class);
                log.info("ZConfig value : {}", zConfig.value());
                // 初始化data
                ReflectionUtil.setFieldContent(bean, field, new ConcurrentHashMap<>());
                // 获取对象
                Object obj = ReflectionUtil.getFieldContent(bean, field);
                if (obj instanceof Map) {
                    Map<String, String> data = (ConcurrentHashMap<String, String>) obj;
                    Map<String, String> dataFromRemote = hotConfigManager.getConfigFromRemote(CommonUtil.getRealKey(beanName, zConfig.value()));
                    data.putAll(dataFromRemote);
                    hotConfigManager.setConfig(CommonUtil.getRealKey(beanName, zConfig.value()), data);
                }
            }
        }
        return bean;
    }


    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
