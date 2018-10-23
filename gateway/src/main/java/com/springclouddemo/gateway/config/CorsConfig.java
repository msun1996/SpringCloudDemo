package com.springclouddemo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * author: mSun
 * date: 2018/10/23
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration configuration = new CorsConfiguration();

        // 设置允许cookie跨域
        configuration.setAllowCredentials(true);
        // 允许跨域访问域名（http://www.a.com）
        configuration.setAllowedOrigins(Arrays.asList("*"));
        // 设置允许头
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 设置允许方法
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 设置多长时间不用再做跨域请求检查
        configuration.setMaxAge(300l);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
