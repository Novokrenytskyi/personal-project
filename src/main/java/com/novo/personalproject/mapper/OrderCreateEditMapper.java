package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.OrderCreateEditDto;
import com.novo.personalproject.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {
    @Override
    public Order map(OrderCreateEditDto object) {
        Order order = new Order();
//        copy(object, order);
        return order;
    }

//    public void copy(OrderCreateEditDto object, Order order) {
//        order.setTimeOfCreation(object.getTimeOfCreation());
//        order.setDeliveryMethod(object.getDeliveryMethod());
//        order.setUser();
//    }
}
