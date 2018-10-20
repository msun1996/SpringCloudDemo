package com.springclouddemo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 接收客户端
 * author: mSun
 * date: 2018/10/19
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.input)
    public void process(Object message) {
        log.info("StreamReceiver:{}", message);
    }
}
