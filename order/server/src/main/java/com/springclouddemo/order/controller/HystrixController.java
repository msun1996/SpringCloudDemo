package com.springclouddemo.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Hystrix测试用例
 * author: mSun
 * date: 2018/10/23
 */
@RestController
// 默认降级调用，@HystrixCommand可不写回调方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    // 异常降级，fallbackMethod回调fallback
    // 超时降级设置，默认1000ms
//    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    // 熔断配置
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //设置熔断
            // 请求次数达到10次后，错误率超过60%,服务默认休眠（不可用）1000ms
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数达到数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"), // 默认服务不可用时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 错误率
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8080/product/listForOrder",
                Arrays.asList("157875227953464068"),
                String.class);
//        throw new RuntimeException("抛错，降级回调");
    }

    // 回调
    private String fallback() {
        return "太拥挤了（服务异常）";
    }

    private String defaultFallback() {
        return "默认降级回调方法";
    }
}
