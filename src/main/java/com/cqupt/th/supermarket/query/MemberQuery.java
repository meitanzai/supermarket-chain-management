package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/30 15:54
 */
@Data
public class MemberQuery implements Serializable {

    private String cardNumber;
    private String name;
    private String tel;
    private Integer status;
    private Date startTime;
    private Date endTime;
}
