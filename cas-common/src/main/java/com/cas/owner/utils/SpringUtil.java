package com.cas.owner.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong
 * @date: 2019/8/13 22:43
 * @version: V1.0
 * @review: xiang_long
 * 1.通过beanId获取spring容器中的实例
 */
@Slf4j
@Component
public class SpringUtil implements BeanFactoryAware, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static BeanFactory beanFactory;

    /**
     * 通过beanId获取spring容器中的实例
     *
     * @param
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return beanFactory == null ? applicationContext.getBean(clazz) : beanFactory.getBean(clazz);
        } catch (Exception e) {
            return getBean(clazz, clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1));
        }
    }

    /**
     * 通过beanId获取spring容器中的实例
     *
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        return beanFactory == null ? applicationContext.getBean(beanId) : beanFactory.getBean(beanId);
    }

    /**
     * 通过bean的id从上下文中拿出该对象
     */
    public static <T> T getBean(Class<T> clazz, String beanId) {
        return beanFactory == null ? applicationContext.getBean(beanId, clazz) : clazz.cast(beanFactory.getBean(beanId));
    }


    @SuppressWarnings("all")
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
        log.info("SpringUtils has been saved beanFactory in a static variable!");
    }

    @SuppressWarnings("all")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
        log.info("SpringUtils has been saved applicationContext in a static variable!");
    }
}
