package com.cas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:26 2020-01-20
 * @version: V1.0
 * @review:
 */
@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.cas")
@Configuration
public class ApplicationTest {

    public static void main(String[] args) {
        try{
            SpringApplication.run(ApplicationTest.class, args);
            System.out.println("测试环境启动成功！！！！");
        } catch (Exception e) {
            System.out.println("测试环境启动失败！！！！");
        }
    }
}
