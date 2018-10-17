package com.springclouddemo.order.utils;

import java.util.Random;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

/**
 * author: mSun
 * date: 2018/10/15
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
