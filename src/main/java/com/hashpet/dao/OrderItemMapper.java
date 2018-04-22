package com.hashpet.dao;

import com.hashpet.pojo.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer ordItemId);

    int insert(List<OrderItem> record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer ordItemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> queryOrderDetailByOrdId(Integer ordId);
}