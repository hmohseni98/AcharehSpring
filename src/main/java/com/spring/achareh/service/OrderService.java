package com.spring.achareh.service;

import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.order.OrderCustomerDTO;
import com.spring.achareh.service.dto.order.OrderExpertDTO;
import com.spring.achareh.service.dto.order.OrderRegisterDTO;

import java.util.List;

public interface OrderService extends BaseService<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllBySpeciality(Speciality speciality);

    List<Order> findAllByStatus(OrderStatus status);

    List<OrderExpertDTO> selectAllByExpert(Integer expertId);

    void orderRegister(Order order);

    List<OrderExpertDTO> selectAllByStatus();

    List<OrderCustomerDTO> selectAllByCustomer(Integer customerId);

}
