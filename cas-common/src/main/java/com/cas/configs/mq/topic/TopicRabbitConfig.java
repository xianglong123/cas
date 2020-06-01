package com.cas.configs.mq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 15:52 2020-05-31
 * @version: V1.0
 * @review:
 */
@Configuration
public class TopicRabbitConfig {

    private final static String MAN = "topic.man";
    private final static String WOMAN = "topic.woman";

    @Bean
    public Queue manQueue() {
        return new Queue(MAN);
    }

    @Bean
    public Queue woManQueue() {
        return new Queue(WOMAN);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(manQueue()).to(topicExchange()).with(MAN);
    }
    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(woManQueue()).to(topicExchange()).with("topic.#");
    }

}
