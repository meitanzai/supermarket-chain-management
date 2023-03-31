package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/30 21:22
 */
@Data
public class MemberPointVo implements Serializable {
    private Integer id;
    private Integer memberId;
    private String memberName;
    private Integer point;
    private String remark;
    private Date gmtCreate;
}
