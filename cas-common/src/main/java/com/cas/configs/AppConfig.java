package com.cas.configs;

import com.cas.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:20 2020-01-20
 * @version: V1.0
 * @review: 扫描
 */
//@ComponentScan(basePackages = {"com.cas.domain"}, excludeFilters = {@ComponentScan.Filter(classes = Merch.class)}) 可以排除调Merch这个类进去ioc spring容器中
@ComponentScan(basePackages = {"com.cas.domain", "com.cas.components"}) // 只会扫描带@Component注解的类
@Configuration
public class AppConfig {

//    通过 @Bean 注入对象
//    @Bean
//    public User initUser() {
//        return new User("xl", "123456");
//    }

}
