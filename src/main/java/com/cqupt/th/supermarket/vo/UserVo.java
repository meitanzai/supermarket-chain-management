package com.cqupt.th.supermarket.vo;

import com.cqupt.th.supermarket.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author th
 * @date 2023/4/13 13:00
 */
@Data
public class UserVo implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String nickName;
    List<Role> roles;
}
