package com.cas.components;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:14 2020-02-25
 * @version: V1.0
 * @review: 日志输入uuid,具体使用查看logback-spring.xml 中的 %X{trace_uuid}
 */
@Slf4j
@Component
public class logComponent {

    @PostConstruct
    public void initMDC () {
        // 日志uuid跟踪注入
        log.info("日志uuid跟踪注入完毕");
        MDC.put("trace_uuid", UUID.randomUUID().toString().replace("-", ""));
    }

}

