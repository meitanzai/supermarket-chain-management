package com.cqupt.th.supermarket.vo;

import com.cqupt.th.supermarket.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author th
 * @date 2023/4/12 21:36
 */
@Data
public class RoleVo implements Serializable {
    private Integer id;
    private String name;
    private List<Permission> permissions;
}
