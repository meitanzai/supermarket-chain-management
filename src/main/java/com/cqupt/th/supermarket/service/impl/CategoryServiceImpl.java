package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Category;
import com.cqupt.th.supermarket.service.CategoryService;
import com.cqupt.th.supermarket.mapper.CategoryMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-03-25 16:22:12
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {


    /**
     * 得到所有类别
     *
     * @return {@link CommonResult}
     */
    @Override
    public CommonResult getAllCategoryWithTree() {
        //1、查出所有分类
        List<Category> entities = baseMapper.selectList(null);
        List<CategoryVo> categoryVoList = entities.stream().map(e -> {
            CategoryVo vo = new CategoryVo();
            BeanUtils.copyProperties(e, vo);
            vo.setLevel(1);
            return vo;
        }).collect(Collectors.toList());
        //2、组装成具有父子关系的树形结构(因为要形成树形结构，所以要有一个List的属性来存子分类)
        //2.1、找到所有一级分类（parentId == 0）
        List<CategoryVo> level1Menus = categoryVoList.stream().filter(e ->
                e.getParentId() == 0
        ).map(e -> {
            //查找子分类
            e.setChildren(getChildren(e, categoryVoList, 2));
            return e;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("items", level1Menus);
    }

    @Override
    public CommonResult deleteCategory(Integer id) {

        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        List<Category> categories = baseMapper.selectList(null);
        //删除三级分类
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        for (Category category : categories) {
            if (category.getParentId().intValue() == id.intValue()) {
                ids.add(category.getId());
                for (Category category1 : categories) {
                    if (category1.getParentId().intValue() == category.getId().intValue()) {
                        ids.add(category1.getId());
                    }
                }
            }
        }
        baseMapper.deleteBatchIds(ids);
        return CommonResult.ok();
    }

    @Override
    public CommonResult getCategoryById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        Category category = baseMapper.selectById(id);
        return CommonResult.ok().data("item", category);
    }

    @Override
    public CommonResult addCategory(Category category) {
        if (category == null) {
            return CommonResult.error().message("category不能为空");
        }
        if (category.getParentId() == null) {
            category.setParentId(0);
        }
        int insert = baseMapper.insert(category);
        if (insert == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok();
    }

    @Override
    public CommonResult updateCategory(Integer id, Category category) {

        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        if (category == null) {
            return CommonResult.error().message("category不能为空");
        }
        category.setId(id);
        int i = baseMapper.updateById(category);
        if (i == 0) {
            return CommonResult.error().message("更新失败");
        }
        return CommonResult.ok();
    }

    @Override
    public Map<Integer, String> getCategoryNameById() {
        List<Category> categories = baseMapper.selectList(null);
        HashMap<Integer, String> map = new HashMap<>();
        for (Category category : categories) {
            map.put(category.getId(), category.getTitle());
        }
        return map;
    }

    @Override
    public Integer[] getCategoryIds(Integer id) {
        Category category = baseMapper.selectById(id);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(category.getId());
        while (category.getParentId() != 0) {
            id = category.getParentId();
            list.add(0, id);
            category = baseMapper.selectById(id);
        }
        Integer[] integers = list.stream().toArray(Integer[]::new);
        return integers;
    }


    private List<CategoryVo> getChildren(CategoryVo currentMenu, List<CategoryVo> all, int level) {
        //找到所有子分类并加入到子分类的List中
        List<CategoryVo> children = all.stream().filter(e -> {
            return e.getParentId().intValue() == currentMenu.getId().intValue();
        }).map(e -> {
            //继续找每个子分类的子分类
            List<CategoryVo> children1 = getChildren(e, all, level + 1);
            if (children1.size() == 0) {
                e.setChildren(null);
            } else {
                e.setChildren(children1);
            }
            e.setLevel(level);
            return e;
        }).collect(Collectors.toList());

        return children;
    }

}



