package com.longyx.user.controller;

import com.longyx.product.VO.ResultVO;
import com.longyx.user.constant.CookieConstant;
import com.longyx.user.constant.RedisConstant;
import com.longyx.user.dataobject.UserInfo;
import com.longyx.user.enums.ResultEnum;
import com.longyx.user.enums.RoleEnum;
import com.longyx.user.service.UserService;
import com.longyx.user.util.CookieUtil;
import com.longyx.user.util.ResultVoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:18
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登陆
     * @author Mr.Longyx
     * @date 2020/1/16 21:39
     * @param openid
     * @param response
     * @return com.longyx.product.VO.ResultVO
     */
    @RequestMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid")String openid, HttpServletResponse response){
        //1.openid和数据库中的数据进行校验
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if(!RoleEnum.BUYER.getCode().equals(userInfo.getRole())){
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.cookie里设置openid=weixin
        CookieUtil.setCookie(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVoUtil.success();
    }

    @RequestMapping("/seller" )
    public ResultVO seller(@RequestParam("openid")String openid, HttpServletRequest request, HttpServletResponse response){
        //1.openid和数据库中的数据进行校验
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if(!RoleEnum.SELLER.getCode().equals(userInfo.getRole())){
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //判断是否已登陆
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if(cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
            return ResultVoUtil.success();
        }

        //设置redis的key
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, expire, TimeUnit.SECONDS);

        //3.cookie里设置token=uuid
        CookieUtil.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVoUtil.success();
    }
}
