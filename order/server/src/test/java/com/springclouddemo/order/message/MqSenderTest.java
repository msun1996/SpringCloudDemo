package com.springclouddemo.order.message;

import com.springclouddemo.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * mq消息发现测试端
 */
@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now" + new Date() );
    }

    @Test
    public void send3() {
        amqpTemplate.convertAndSend("myExchange","key1", "now" + new Date() );
    }


}