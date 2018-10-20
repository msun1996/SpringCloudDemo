package com.springclouddemo.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * author: mSun
 * date: 2018/10/19
 */
public interface StreamClient {

    String input = "input";
    String output = "output";

    @Input(StreamClient.input)
    SubscribableChannel input();

    @Output(StreamClient.output)
    MessageChannel output();

}
