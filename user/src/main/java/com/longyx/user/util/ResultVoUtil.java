package com.longyx.user.util;

import com.longyx.product.VO.ResultVO;
import com.longyx.user.enums.ResultEnum;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:59
 */
public class ResultVoUtil {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMsg("数据构造成功");

        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("数据构造成功");

        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());

        return resultVO;
    }
}
