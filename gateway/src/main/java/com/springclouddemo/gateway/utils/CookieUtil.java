package com.springclouddemo.gateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: mSun
 * date: 2018/10/23
 */
public class CookieUtil {

    /**
     * 设置cookie
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie: cookies) {
                if(name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
