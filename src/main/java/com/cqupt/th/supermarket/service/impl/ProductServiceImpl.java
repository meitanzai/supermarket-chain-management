package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.query.ProductQuery;
import com.cqupt.th.supermarket.service.BrandService;
import com.cqupt.th.supermarket.service.CategoryService;
import com.cqupt.th.supermarket.service.ProductService;
import com.cqupt.th.supermarket.mapper.ProductMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 16075
 * @description 针对表【product】的数据库操作Service实现
 * @createDate 2023-03-26 19:55:57
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Autowired
    @Qualifier("brandService")
    private BrandService brandService;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Override
    public CommonResult getProductByPage(Integer currentPage, Integer size, ProductQuery productQuery) {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        if (productQuery != null) {
            if (productQuery.getName() != null) {
                productQueryWrapper.like("name", productQuery.getName());
            }
            if (productQuery.getIsShow() != null) {
                productQueryWrapper.eq("is_show", productQuery.getIsShow());
            }
            if (productQuery.getBarcode() != null) {
                productQueryWrapper.eq("barcode", productQuery.getBarcode());
            }
            if (productQuery.getCategoryId() != null) {
                productQueryWrapper.eq("category_id", productQuery.getCategoryId());
            }
            if (productQuery.getBrandId() != null) {
                productQueryWrapper.eq("brand_id", productQuery.getBrandId());
            }
            if (productQuery.getStartTime() != null) {
                productQueryWrapper.ge("gmt_create", productQuery.getStartTime());
            }
            if (productQuery.getEndTime() != null) {
                productQueryWrapper.le("gmt_create", productQuery.getEndTime());
            }
        }
        productQueryWrapper.orderByDesc("gmt_modified");
        Page<Product> productPage = new Page<>(currentPage, size);
        baseMapper.selectPage(productPage, productQueryWrapper);
        List<Product> records = productPage.getRecords();
        List<ProductVo> productVos = new ArrayList<>();
        Map<Integer, String> brandMap = brandService.getBrandNameById();
        Map<Integer, String> categoryMap = categoryService.getCategoryNameById();
        for (Product record : records) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(record, productVo);
            productVo.setBrandName(brandMap.get(record.getBrandId()));
            productVo.setCategoryName(categoryMap.get(record.getCategoryId()));
            productVos.add(productVo);
        }

        return CommonResult.ok().data("total", productPage.getTotal()).data("rows", productVos);
    }

    @Override
    public CommonResult updateProductById(Integer id, Product product) {
        if (product == null) {
            return CommonResult.error().message("参数不能为空");
        }
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        product.setId(id);
        int i = baseMapper.updateById(product);
        if (i > 0) {
            return CommonResult.ok().message("修改成功");
        }
        return CommonResult.error().message("修改失败");
    }

    @Override
    public CommonResult deleteProductByIds(Integer[] ids) {

        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("参数不能为空");
        }
        int i = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (i > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult deleteProductById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("参数不能为空");
        }
        int i = baseMapper.deleteById(id);
        if (i > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult addProduct(Product product) {
        if (product == null) {
            return CommonResult.error().message("参数不能为空");
        }
        product.setIsShow(1);
        int i = baseMapper.insert(product);
        if (i > 0) {
            return CommonResult.ok().message("添加成功");
        }
        return CommonResult.error().message("添加失败");
    }

    @Override
    public CommonResult getCategoryIds(Integer id) {
        if (id == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Integer[] ids = categoryService.getCategoryIds(id);
        return CommonResult.ok().data("item", ids);
    }

    @Override
    public CommonResult getProductByBrandIdAndCategoryId(Integer brandId, Integer categoryId) {
        if (brandId == null || categoryId == null) {
            return CommonResult.error().message("参数不能为空");
        }
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("brand_id", brandId);
        productQueryWrapper.eq("category_id", categoryId);
        List<Product> products = baseMapper.selectList(productQueryWrapper);
        return CommonResult.ok().data("items", products);
    }

    @Override
    public CommonResult getProductById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Product product = baseMapper.selectById(id);
        return CommonResult.ok().data("item", product);
    }


}




