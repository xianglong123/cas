package com.cas.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 19:33 2020-02-16
 * @version: V1.0
 * @review:
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if(this.redisConnectionFactory != null) {
            return this.redisConnectionFactory;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(30);
        //最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        //创建 Jedis 连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);

        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("");
        this.redisConnectionFactory = connectionFactory;
        return redisConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }


}
