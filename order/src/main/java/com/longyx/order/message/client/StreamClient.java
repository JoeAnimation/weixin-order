package com.longyx.order.message.client;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 22:43
 */
public interface StreamClient {
    String INPUT = "messageInfo";

    String RESPONSE = "responseMessage";
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();

    @Input(StreamClient.RESPONSE)
    SubscribableChannel request();

    @Output(StreamClient.RESPONSE)
    MessageChannel response();

}
