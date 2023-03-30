package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author th
 * @date 2023/3/25 16:34
 */
@Data
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private Integer parentId;

    private List<CategoryVo> children;

    private Integer level;
}
