package com.spring.achareh.service;

import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.service.base.BaseService;
import java.util.List;

public interface OrderService extends BaseService<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllByService(Speciality speciality);

    List<Order> findAllByExpert(Expert expert);

    List<Order> findAllByStatus(OrderStatus status);
}
