package com.cas.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:37 2020-01-20
 * @version: V1.0
 * @review:
 */
@Component
public class BeanPostProcessorExample implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor调用" +
                "postProcessBeforeInitialization 方法，参数【"
                + bean.getClass().getSimpleName() + "】【" + beanName +"】");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor调用" +
                "postProcessBeforeInitialization 方法，参数【"
                + bean.getClass().getSimpleName() + "】【" + beanName +"】");
        return bean;
    }
}
