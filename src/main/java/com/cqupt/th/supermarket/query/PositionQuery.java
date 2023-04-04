package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/31 16:27
 */
@Data
public class PositionQuery implements Serializable {

    private String name;
    private Date startTime;
    private Date endTime;
}
