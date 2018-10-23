package com.springclouddemo.user.controller;

import com.springclouddemo.user.VO.ResultVO;
import com.springclouddemo.user.constant.CookieConstant;
import com.springclouddemo.user.constant.RedisContant;
import com.springclouddemo.user.domain.UserInfo;
import com.springclouddemo.user.enums.ResultEnum;
import com.springclouddemo.user.enums.RoleEnum;
import com.springclouddemo.user.service.UserService;
import com.springclouddemo.user.utils.CookieUtil;
import com.springclouddemo.user.utils.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author: mSun
 * date: 2018/10/22
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 买家登录
     * @param openid
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        // 1.openid和数据库的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2.判断角色
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 3.cookie里配置openid
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        // 0.判断是否已登录
        // 获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(
                String.format(RedisContant.TOKEN_TEMPLATE, cookie.getValue())))
                ) {
            return ResultVOUtil.success();
        }

        // 1.openid和数据库的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2.判断角色
        if (userInfo.getRole() != RoleEnum.SELLER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 3.redis设置key=UUID,value=openid
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisContant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);
        // 4.cookie里配置token=UUID
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }
}
