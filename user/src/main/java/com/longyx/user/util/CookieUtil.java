package com.longyx.user.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置cookie
 * @author Mr.Longyx
 * @date 2020年01月16日 22:10
 */
public class CookieUtil {
    /**
     * 设置cookie信息
     * @author Mr.Longyx
     * @date 2020/1/16 22:13
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie信息
     * @author Mr.Longyx
     * @date 2020/1/16 23:18
     * @param request
     * @param name
     * @return javax.servlet.http.Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
