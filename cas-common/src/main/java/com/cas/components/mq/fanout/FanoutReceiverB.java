package com.cas.components.mq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@Slf4j
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {
 
    @RabbitHandler
    public void process(Map testMessage) {
        log.info("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
    }
 
}