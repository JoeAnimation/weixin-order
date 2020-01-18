package com.longyx.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受mq的消息
 * @author Mr.Longyx
 * @date 2020年01月13日 21:24
 */
@Slf4j
@Component
public class MqReceiver {
    /**
     * 方式一：手动创建队列
     * @author Mr.Longyx
     * @date 2020/1/13 21:41
     * @param message
     *  @RabbitListener(queues = "msgQueue")
     *   public void process(String message){
     *   log.info("mqReceiver: {}", message);
     *  }
     */

    /**
     * 方式二：自动创建队列
     * @author Mr.Longyx
     * @date 2020/1/13 21:47
     * @param message
     * @RabbitListener(queuesToDeclare = @Queue("myMqQueue"))
     *  public void msgListener(String message){
     *     log.info("mqReceiver: {}", message);
     * }
     */

    /**
     * 方式三：自动创建Exchange和Queue绑定
     * @author Mr.Longyx
     * @date 2020/1/13 22:04
     * @param message
     * @RabbitListener(bindings = @QueueBinding(value = @Queue("mqQueue"),exchange = @Exchange("exchangeable")))
     *     public void process(String message){
     *         log.info("mqReceiver: {}", message);
     *     }
     */

    /**
     * 数码供应商服务 接收特定(key)消息
     * @author Mr.Longyx
     * @date  22:19
     */
    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange("orderService"), key = "digit", value = @Queue("digitOrder")))
    public void processDigit(String message){
        log.info("digit MqReceiver: {}", message);
    }

    /**
     * 水果供应商服务 接收特定(key)消息
     * @author Mr.Longyx
     * @date  22:19
     */
    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange("orderService"), key = "fruit", value = @Queue("digitOrder")))
    public void processFruit(String message){
        log.info("fruit MqReceiver: {}", message);
    }
}
