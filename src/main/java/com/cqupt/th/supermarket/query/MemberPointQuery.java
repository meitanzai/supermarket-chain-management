package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/30 19:13
 */
@Data
public class MemberPointQuery implements Serializable {
    private Integer memberId;
    private Integer point;
    private Date startTime;
    private Date endTime;
}
