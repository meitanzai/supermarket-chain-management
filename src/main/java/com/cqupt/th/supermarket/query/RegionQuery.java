package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author th
 * @date 2023/3/28 20:03
 */
@Data
public class RegionQuery implements Serializable {
    private Integer parentId;
}
