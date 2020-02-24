package com.cas.config;

import com.cas.configs.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

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
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        // 直接使用redisTemplate处理多个读写 可能会造成资源浪费，会进行多次连接与回收
        //可以使用redisCallback 和 SessionCallback 接口来统一操作，多个读写会在一个连接中进行，节约资源
        //第一种：好处-简介，方便 坏处-存在资源浪费，多次连接与回收
//        redisTemplate.opsForValue().set("key1", "value1");
//        redisTemplate.opsForHash().put("hash", "field", "hvalue");
        //第二种：
//        redisTemplate.execute((RedisOperations ro) -> {
//            ro.opsForValue().set("key1", "value1");
//            ro.opsForHash().put("hash", "field", "hvalue");
//            return null;
//        });
        new SessionCallback() {
            @Override
            public Object execute(RedisOperations ro) throws DataAccessException {
                ro.opsForValue().set("key1", "value2");
                ro.opsForHash().put("hash", "field", "hvalue");
                return null;
            }
        }.execute(redisTemplate);
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

}
