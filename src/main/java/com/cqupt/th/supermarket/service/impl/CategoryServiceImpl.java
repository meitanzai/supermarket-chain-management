package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Category;
import com.cqupt.th.supermarket.mapper.ProductMapper;
import com.cqupt.th.supermarket.service.CategoryService;
import com.cqupt.th.supermarket.mapper.CategoryMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private ProductMapper productMapper;


    /**
     * 得到所有类别树
     *
     * @return {@link CommonResult}
     */
    @Override
    public CommonResult getAllCategoryWithTree() {
        //1、查出所有分类
        List<Category> entities = baseMapper.selectList(new QueryWrapper<Category>().orderByDesc("gmt_modified"));
        if (entities == null || entities.size() == 0) {
            return CommonResult.ok().data("items", null);
        }
        List<CategoryVo> categoryVoList = entities.stream().map(e -> {
            CategoryVo vo = new CategoryVo();
            BeanUtils.copyProperties(e, vo);
            vo.setLevel(1);
            return vo;
        }).collect(Collectors.toList());
        //2、组装成具有父子关系的树形结构(因为要形成树形结构，所以要有一个List的属性来存子分类)
        //2.1、找到所有一级分类（parentId == 0）
        List<CategoryVo> level1 = categoryVoList.stream().filter(e ->
                e.getParentId() == 0
        ).map(e -> {
            //查找子分类
            List<CategoryVo> children = getChildren(e, categoryVoList, 2);
            if (children != null && children.size() > 0) {
                e.setChildren(children);
            } else {
                e.setChildren(null);
            }
            return e;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("items", level1);
    }

    @Override
    public CommonResult deleteCategoryById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
//        递归删除子目录
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<Integer> level2 = baseMapper.selectList(new QueryWrapper<Category>().eq("parent_id", id)).stream().map(e -> {
            ids.add(e.getId());
            return e.getId();
        }).collect(Collectors.toList());
        if (level2 != null && level2.size() > 0) {
            List<Integer> level3 = baseMapper.selectList(new QueryWrapper<Category>().in("parent_id", level2)).stream().map(e -> {
                ids.add(e.getId());
                return e.getId();
            }).collect(Collectors.toList());
            if (level3 != null && level3.size() > 0) {
                baseMapper.selectList(new QueryWrapper<Category>().in("parent_id", level3)).stream().map(e -> {
                    ids.add(e.getId());
                    return e.getId();
                }).collect(Collectors.toList());
            }
        }
        //TODO 商品
//        productMapper.updateCategoryIdByCategoryIds(ids);
        int result = baseMapper.deleteBatchIds(ids);
        if (result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok();
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
    public CommonResult updateCategoryById(Integer id, Category category) {

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
    public Integer[] getCategoryIds(Integer id) {
        Category category = baseMapper.selectById(id);
        ArrayList<Integer> list = new ArrayList<>();
        if (category == null) {
            return new Integer[]{};
        }
        list.add(category.getId());
        while (category.getParentId() != 0) {
            id = category.getParentId();
            list.add(0, id);
            category = baseMapper.selectById(id);
        }
        Integer[] integers = list.stream().toArray(Integer[]::new);
        return integers;
    }


    private List<CategoryVo> getChildren(CategoryVo current, List<CategoryVo> all, int level) {
        //找到所有子分类并加入到子分类的List中
        List<CategoryVo> children = all.stream().filter(e ->
                e.getParentId().equals(current.getId())
        ).map(e -> {
            //继续找每个子分类的子分类
            List<CategoryVo> children1 = getChildren(e, all, level + 1);
            if (children1 == null || children1.size() == 0) {
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



