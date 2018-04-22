package com.hashpet.dao;

import com.hashpet.pojo.URLImage;
import org.springframework.stereotype.Repository;

@Repository
public interface URLImageMapper {
    int deleteByPrimaryKey(String urlId);

    int insert(URLImage record);

    int insertSelective(URLImage record);

    URLImage selectByPrimaryKey(String urlId);

    URLImage selectByGoodsId(String goodsId);

    int updateByPrimaryKeySelective(URLImage record);

    int updateByPrimaryKey(URLImage record);
}