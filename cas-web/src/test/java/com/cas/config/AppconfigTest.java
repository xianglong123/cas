package com.cas.config;

import com.cas.configs.AppConfig;
import com.cas.domain.Merch;
import com.cas.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:24 2020-01-20
 * @version: V1.0
 * @review:  可以通过扫描
 */
@Slf4j
public class AppconfigTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        System.out.println(user.getUsername());
        System.out.println(user.getAge());
        System.out.println(user.isSex());
        System.out.println(user.getInitTime());

        Merch merch = ctx.getBean(Merch.class);
        System.out.println(merch.getAlie());
        System.out.println(merch.getUsername());
    }

}
