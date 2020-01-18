package com.longyx.product.utils;

import com.longyx.product.VO.ResultVO;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 22:38
 */
public class ResultVoUtil {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMsg("数据构造成功");

        return resultVO;
    }
}
