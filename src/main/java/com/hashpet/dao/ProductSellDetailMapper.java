package com.hashpet.dao;

import com.hashpet.pojo.ProductSellDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSellDetailMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(ProductSellDetail record);

    int insertSelective(ProductSellDetail record);

    ProductSellDetail selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(ProductSellDetail record);

    int updateByPrimaryKey(ProductSellDetail record);
}