package com.cas.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:20 2020-01-20
 * @version: V1.0
 * @review:
 */
//@ComponentScan(basePackages = {"com.cas.domain"}, excludeFilters = {@ComponentScan.Filter(classes = Merch.class)}) 可以排除调Merch这个类进去ioc spring容器中
@ComponentScan(basePackages = {"com.cas.domain", "com.cas.service", "com.cas.configs"})
@Configuration
public class AppConfig {

//    通过 @Bean 注入对象
//    @Bean
//    public User initUser() {
//        return new User("xl", "123456");
//    }

}
