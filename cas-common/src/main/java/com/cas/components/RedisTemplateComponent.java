package com.cas.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:41 2020-02-18
 * @version: V1.0
 * @review: 模版 + 订阅监听器绑定处理器 + 自定义缓存管理器
 */
//@Component
public class RedisTemplateComponent {

    @Autowired
    private RedisTemplate redisTemplate;

    // Redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    // Redis消息监听器
    @Autowired
    private RedisMessageListener redisMsgListener = null;

    // 任务池
    private ThreadPoolTaskScheduler taskScheduler = null;


    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    // 设置RedisTemplate的序列化器
    @SuppressWarnings("unchecked")
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    /**
     * 创建任务池，运行线程等待处理Redis的消息
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义 Redis 的监听容器
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        // Redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道， 名称为topicl
        Topic topic = new ChannelTopic("topicl");
//        Topic topic2 = new ChannelTopic("topicl2");
        // 使用监听器监听 Redis 的消息  这步将监听器和处理器绑定在一起  redisMsgListener 这个只监听 "topicl" 这个主题的消息
        // 这里可以多监听处理器绑定主题
        container.addMessageListener(redisMsgListener, topic);
//        container.addMessageListener(redisMsgListener2, topic2);
        return container;
    }

    /**
     * 自定义缓存管理器
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager() {
        // Redis 加锁的写入器
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        //默认超时时间，单位秒
        redisCacheManager.setDefaultExpiration(60);
//        //缓存超时时间Map，key为cacheName，value为超时,单位是秒
//        Map<String, Long> expiresMap = new HashMap<>();
//        //缓存用户信息的cacheName和超时时间
//        expiresMap.put("user", 1800L);
//        //缓存产品信息的cacheName和超时时间
//        expiresMap.put("product", 600L);
//        redisCacheManager.setExpires(expiresMap);
        return redisCacheManager;
    }


}
