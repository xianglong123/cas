package com.cas;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: xianglong
 * @date: 2019/8/13 18:36
 * @version: V1.0
 * @review: xiang_long
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.cas")
//@MapperScan(basePackages = "com.cas.dao.mapper")
//@EnableCaching
//@EnableScheduling
public class UpmApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(UpmApplication.class, args);
            log.info("#############################################");
            log.info("#####-----启动成功-----#####");
            log.info("#############################################");
        } catch (Exception e) {
            log.info("#############################################");
            log.info("#####启动失败#####");
            log.info("#############################################");
        }
    }
}
