package com.longyx.order.util;

import com.longyx.order.VO.ResultVO;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:35
 */
public class ResultVOUtil {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(obj);

        return resultVO;
    }
}