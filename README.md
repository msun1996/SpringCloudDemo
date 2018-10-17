# SpringCloudDemo
商品微服务
# Eureka 服务注册与发现
- ***Eureka Server***  注册中心
- ***Eureka Client***  服务注册
(心跳连接)
### 服务发现分两种方法
- 客户端发现(客户端可知道后端服务数量，ip)
 - Eureka
- 服务端发现(代理。客户端对后端服务不可知)
 - Nginx
 - Zookeeper
# 微服务通信方式
- HTTP
 - SpringCloud
- RPC
 - Dubbo
# SpringCloud应用间通信方式
## 应用A
- ***ServerController***
```java
@RestController
@RequestMapping
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product";
  }
}
```
## 应用B
### RestTemplate实现
- ***ClientController***
```java
package com.springclouddemo.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * author: mSun
 * date: 2018/10/15
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 方法一（直接使用restTemplate,url写死）
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://127.0.0.1:8080/msg";
//        String response = restTemplate.getForObject(url,String.class);

        // 方法二（利用loadBalancerClient通过应用名获取url,然后使用restTemplate获取）
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        RestTemplate restTemplate = new RestTemplate();
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()+"/msg");
//        String response = restTemplate.getForObject(url,String.class);

        // 方式三(利用@LoadBalanced进行注解配置，可在restTemplate里使用引用名)
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        log.info("response={}", response);
        return response;
    }
}

```
- ***RestTemplateConfig***
```java
package com.springclouddemo.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```
##### Robbin 上面利用LoadBalanced实现软负载均衡(默认轮循)
###### 主流程插件：
 - ServerList 获取所有可用服务列表
 - ServerListFilter 过滤一部分地址
 - IRule 选择一个实例作为请求地址
### Feign实现（伪RPC通信）
- ***pom***
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.4.5.RELEASE</version>
</dependency>
```
- ***Application***加注解
```java
@EnableFeignClients
```
- ***client.ProductClient***服务端请求映射
```java
package com.springclouddemo.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author: mSun
 * date: 2018/10/16
 */
@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/msg")
    String productMsg();
}

```
- ***CleintController***
```java
package com.springclouddemo.order.controller;

import com.springclouddemo.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * author: mSun
 * date: 2018/10/15
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }
}

```
