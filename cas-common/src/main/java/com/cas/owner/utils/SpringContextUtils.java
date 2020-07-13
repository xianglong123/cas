package com.cas.owner.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:40 2020-01-17
 * @version: V1.0
 * @review: 实现 ApplicationContextAware 即可以使用所有容器中的 Bean
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
        //获取环境
        Environment environment = applicationContext.getEnvironment();
        //这个获得的是配置的运行环境，如开发，测试，生产
        String[] activeProfiles = environment.getActiveProfiles();
        //这个获取的是当前项目默认的运行环境，一般设置成开发
        String[] defaultProfiles = environment.getDefaultProfiles();
    }
}
