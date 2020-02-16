package com.cas.config;

import com.cas.configs.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 21:30 2020-02-16
 * @version: V1.0
 * @review:
 */
public class RedisConfigTest {

    //mac 启动redis 命令行执行 redis-server
    //mac 启动redis桌面 命令行执行 redis-cli
    /**
     * 测试redis 这里可以通过springboot的配置来创建IOC 容器，这时容器里面就只有若干个想用到的Bean.简化启动
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "field", "hvalue");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

}
