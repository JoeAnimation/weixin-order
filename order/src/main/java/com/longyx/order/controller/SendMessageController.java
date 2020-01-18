package com.longyx.order.controller;

import com.longyx.order.dto.OrderDTO;
import com.longyx.order.message.client.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 22:49
 */
@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMsg")
    public void process(){
        String message = "now: "+String.valueOf(new Date());
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送方 orderDTO对象 消息
     * @author Mr.Longyx
     * @date 2020/1/14 0:21
     */
    @GetMapping("/sendMessage")
    public void sendMsg(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
