package com.hashpet.service.implement;

import com.hashpet.dao.OrderItemMapper;
import com.hashpet.dao.OrderMapper;
import com.hashpet.pojo.Order;
import com.hashpet.pojo.OrderItem;
import com.hashpet.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderService implements IOrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Integer newOrder(Order order) {
        return orderMapper.insert(order);
    }

    public void insertOrderItem(List<OrderItem> item) {
        orderItemMapper.insert(item);
    }

    public Order queryOrderByNum(Order order) {
        return null;
    }

    public List<Order> queryOrderByPhone(Order order) {
        return null;
    }

    public List<Order> queryOrderByUserId(Integer userId) {
        return orderMapper.queryOrderByUserId(userId);
    }

    public List<OrderItem> queryOrderDetailByOrdId(Integer userId) {
        return orderItemMapper.queryOrderDetailByOrdId(userId);
    }

    public void updateOrder(Order order) {

    }

    public void dropOrder(Integer ordId) {

    }


}
