package com.springclouddemo.order.utils;

import com.springclouddemo.order.VO.ResultVO;

/**
 * author: mSun
 * date: 2018/10/15
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
