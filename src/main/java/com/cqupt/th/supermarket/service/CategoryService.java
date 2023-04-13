package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.utils.CommonResult;

import java.util.Map;

/**
* @author 16075
* @description 针对表【category】的数据库操作Service
* @createDate 2023-03-25 16:22:12
*/
public interface CategoryService extends IService<Category> {

    /**
     * 得到所有类别
     *
     * @return {@link CommonResult}
     */
    CommonResult getAllCategoryWithTree();

    CommonResult deleteCategoryById(Integer id);

    CommonResult addCategory(Category category);

    CommonResult updateCategoryById(Integer id, Category category);

    Integer[] getCategoryIds(Integer id);
}
