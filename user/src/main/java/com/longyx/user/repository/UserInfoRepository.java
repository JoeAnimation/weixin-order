package com.longyx.user.repository;

import com.longyx.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:13
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
