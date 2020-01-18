package com.longyx.user.service.impl;

import com.longyx.user.dataobject.UserInfo;
import com.longyx.user.repository.UserInfoRepository;
import com.longyx.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:16
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
