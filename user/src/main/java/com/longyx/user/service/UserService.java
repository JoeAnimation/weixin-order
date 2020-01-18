package com.longyx.user.service;

import com.longyx.user.dataobject.UserInfo;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:14
 */
public interface UserService {
    /**
     * 通过openid查询用户信息
     * @author Mr.Longyx
     * @date 2020/1/16 21:15
     * @param openid
     * @return com.longyx.user.dataobject.UserInfo
     */
    UserInfo findByOpenid(String openid);
}
