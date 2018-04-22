package com.hashpet.dao;

import com.hashpet.pojo.Product;
import com.hashpet.pojo.ProductSellDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductSellDetail> queryByPriority(@Param("limitNum") Integer limitNum);

    List<ProductSellDetail> queryByRandom(@Param("limitNum") Integer limitNum);

    List<ProductSellDetail> queryByConditions(@Param("params") Map<String,Object> params);
}