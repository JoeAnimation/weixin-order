package com.longyx.order.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 发送mq消息测试
 * @author Mr.Longyx
 * @date 2020年01月13日 21:27
 */
@SpringBootTest
class MqSenderTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void sendMsg(){
        amqpTemplate.convertAndSend("mqQueue", "now"+ String.format(String.valueOf(new Date())));
    }

    @Test
    void sendOrder(){
        amqpTemplate.convertAndSend("orderService", "digit","now"+ String.format(String.valueOf(new Date())));
    }
}
