package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Brand;
import com.cqupt.th.supermarket.query.BrandQuery;
import com.cqupt.th.supermarket.service.BrandService;
import com.cqupt.th.supermarket.mapper.BrandMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 16075
 * @description 针对表【brand】的数据库操作Service实现
 * @createDate 2023-03-26 14:31:06
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
        implements BrandService {

    @Override
    public CommonResult getAllBrand() {
        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();
        brandQueryWrapper.eq("is_show", 1);
        brandQueryWrapper.orderByDesc("gmt_modified");
        List<Brand> brands = baseMapper.selectList(brandQueryWrapper);
        return CommonResult.ok().data("items", brands);
    }

    @Override
    public CommonResult getBrandByPage(Integer page, Integer size, BrandQuery brandQuery) {
        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();
        if (brandQuery != null) {
            if (brandQuery.getName() != null) {
                brandQueryWrapper.like("name", brandQuery.getName());
            }
            if (brandQuery.getIsShow() != null) {
                brandQueryWrapper.eq("is_show", brandQuery.getIsShow());
            }
            if (brandQuery.getStartTime() != null) {
                brandQueryWrapper.ge("gmt_create", brandQuery.getStartTime());
            }
            if (brandQuery.getEndTime() != null) {
                brandQueryWrapper.le("gmt_create", brandQuery.getEndTime());
            }
        }
        brandQueryWrapper.orderByDesc("gmt_modified");
        Page<Brand> brandPage = new Page<>(page, size);
        baseMapper.selectPage(brandPage, brandQueryWrapper);
        return CommonResult.ok().data("total", brandPage.getTotal()).data("rows", brandPage.getRecords());
    }

    @Override
    public CommonResult updateBrandById(Integer id, Brand brand) {

        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        if (brand == null) {
            return CommonResult.error().message("brand不能为空");
        }
        brand.setId(id);
        int result = baseMapper.updateById(brand);
        if (result == 1) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }


    }

    @Override
    public CommonResult getBrandById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        Brand brand = baseMapper.selectById(id);
        return CommonResult.ok().data("item", brand);
    }

    @Override
    public CommonResult addBrand(Brand brand) {
        if (brand == null) {
            return CommonResult.error().message("brand不能为空");
        }
        brand.setIsShow(1);
        int result = baseMapper.insert(brand);
        if (result == 1) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    @Override
    public CommonResult deleteBrandById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        int result = baseMapper.deleteById(id);
        if (result == 1) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    @Override
    public CommonResult deleteBrandByIds(Integer[] ids) {

        if (ids == null) {
            return CommonResult.error().message("ids不能为空");
        }
        int result = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (result > 0) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    @Override
    public Map<Integer, String> getBrandNameById() {
        List<Brand> brands = baseMapper.selectList(null);
        HashMap<Integer, String> map = new HashMap<>();
        for (Brand brand : brands) {
            map.put(brand.getId(), brand.getName());
        }
        return map;

    }

    @Override
    public CommonResult getBrandIsShow(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        Brand brand = baseMapper.selectById(id);
        return CommonResult.ok().data("item", brand.getIsShow());
    }
}




