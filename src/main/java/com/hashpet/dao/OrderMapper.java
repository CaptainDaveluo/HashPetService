package com.hashpet.dao;

import com.hashpet.pojo.Order;
import com.hashpet.pojo.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer ordId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer ordId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateByNumSelective(Order record);

    List<Order> queryOrderByUserId(Integer userId);

    List<Order> queryOrderBySellerId(Integer sellerId);
}