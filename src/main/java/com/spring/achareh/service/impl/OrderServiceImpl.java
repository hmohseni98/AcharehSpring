package com.spring.achareh.service.impl;

import com.spring.achareh.model.*;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OrderRepository;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer, OrderRepository>
        implements OrderService {
    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<Order> findAllyByCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Order> findAllByService(Speciality speciality) {
        return null;
    }

    @Override
    public List<Order> findAllByExpert(Expert expert) {
        return null;
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        return null;
    }
}
