package com.cas.config;

import com.cas.configs.AppConfig;
import com.cas.domain.Merch;
import com.cas.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:24 2020-01-20
 * @version: V1.0
 * @review: 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。避免使用application.xml进行配置。相比XML配置，更加便捷。
 */
@Slf4j
public class AppconfigTest {

    public static void main(String[] args) {
        // 可以加载spring的上下文
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        // 销毁上下文
//        ctx.close();

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
