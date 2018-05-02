package com.hashpet.service;

import com.hashpet.pojo.Order;
import com.hashpet.pojo.OrderItem;

import java.util.List;

public interface IOrderService {
    public Integer newOrder(Order order);
    public void insertOrderItem(List<OrderItem> item);
    public Order queryOrderByNum(Order order);
    public List<Order> queryOrderByPhone(Order order);
    public List<Order> queryOrderByUserId(Integer userId);
    public List<OrderItem> queryOrderDetailByUserId(Integer userId);
    public List<Order> queryOrderBySellerId(Integer sellerId);
    public List<OrderItem> queryOrderDetailBySellerId(Integer userId);
    public void updateOrderByNum(Order order);
    public  void dropOrder(Integer ordId);
}
