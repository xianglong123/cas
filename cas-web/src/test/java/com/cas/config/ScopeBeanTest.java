package com.cas.config;

import com.cas.configs.AppConfig;
import com.cas.components.ScopeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:54 2020-01-20
 * @version: V1.0
 * @review:
 */
@Slf4j
public class ScopeBeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ScopeBean scopeBean1 = ctx.getBean(ScopeBean.class);
        ScopeBean scopeBean2 = ctx.getBean(ScopeBean.class);
        System.out.println(scopeBean1 == scopeBean2);
    }


}
