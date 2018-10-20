package com.springclouddemo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 * author: mSun
 * date: 2018/10/18
 */
@Slf4j
@Component
public class MqReceiver {

    //1.需手动创建Queue
    //@RabbitListener(queues = "myQueue")
    //2.自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3.自动创建，同时使交换机,路由key,队列绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            key = "key1",
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }
}
