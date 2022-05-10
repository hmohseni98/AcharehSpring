package com.spring.achareh.repository;


import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Service;
import com.spring.achareh.model.enumration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllByService(Service service);

    List<Order> findAllByExpert(Expert expert);

    List<Order> findAllByStatus(OrderStatus status);
}
