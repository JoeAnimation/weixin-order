package com.longyx.user.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:04
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
@ToString
public class UserInfo implements Serializable {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
