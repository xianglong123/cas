package com.cas.config;

import com.cas.configs.Appconfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:41 2020-01-20
 * @version: V1.0
 * @review:
 */
@Slf4j
public class BeanPostProcessorExampleTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appconfig.class);
        ctx.close();
    }
}
