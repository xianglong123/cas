package com.cas.config;

import com.cas.configs.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:41 2020-01-20
 * @version: V1.0
 * @review: 销毁上下文，这样你就取不到任何bean了，因为你是个逗比
 */
@Slf4j
public class BeanPostProcessorExampleTest {

    public static void main(String[] args) {
        // 注意AppConfig.class 扫描范围和限制
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.close();
    }
}
