package com.longyx.order.message.client;

import com.longyx.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 22:46
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    /**
     *
     * @author Mr.Longyx
     * @date 2020/1/14 0:26
     * @param message
     * @StreamListener(StreamClient.INPUT)
     * public void process(Object message){
     *    log.info("StreamReceiver: {}", message);
     * }
     */

    /**
     * 接收orderDTO对象 消息
     * @author Mr.Longyx
     * @date 2020/1/14 0:26
     * @param message
     */
    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.RESPONSE)
    public String requestMsg(OrderDTO message){
        log.info("StreamReceive: {}", message);
        return "received message!";
    }

    @StreamListener(value = StreamClient.RESPONSE)
    public void responseMsg(String message){
        log.info("responseMsg: {}", message);
    }
}
