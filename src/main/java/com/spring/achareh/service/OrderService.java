package com.spring.achareh.service;

import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.service.base.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends BaseService<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllBySpeciality(Speciality speciality);

    List<Order> findAllByExpert(Integer expertId);

    List<Order> findAllByStatus(OrderStatus status);

    void registerOrder(Integer customer_id,Integer speciality_id , Integer suggestionPrice,
                       String description, LocalDate workDate, String address);
}
