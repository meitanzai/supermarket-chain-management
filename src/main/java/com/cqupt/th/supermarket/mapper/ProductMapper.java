package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 16075
 * @description 针对表【product】的数据库操作Mapper
 * @createDate 2023-03-26 19:55:57
 * @Entity com.cqupt.th.supermarket.entity.Product
 */
public interface ProductMapper extends BaseMapper<Product> {

    void updateCategoryIdByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    void updateBrandIdByBrandId(@Param("brandId") Integer brandId);

    void updateBrandIdByBrandIds(@Param("brandIds") List<Integer> brandIds);

    void updatePurchasePrice(@Param("productId") Integer productId, @Param("purchasePrice") BigDecimal purchasePrice);

    void updatePromotionPriceById(@Param("id") Integer id,@Param("promotionalPrice") BigDecimal promotionalPrice);

}




