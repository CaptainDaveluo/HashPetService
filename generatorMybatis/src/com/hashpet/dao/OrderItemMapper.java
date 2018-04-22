package com.hashpet.dao;

import com.hashpet.pojo.OrderItem;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer ordItemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer ordItemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}