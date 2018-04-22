package com.hashpet.dao;

import com.hashpet.pojo.SellerInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKeyWithBLOBs(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);

    SellerInfo selectByUserId(Integer userId);
}