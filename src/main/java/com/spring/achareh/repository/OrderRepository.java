package com.spring.achareh.repository;


import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllBySpeciality(Speciality speciality);

    @Query(value = "select * from orders " +
            "inner join offer o on orders.id = o.order_id " +
            "where o.expert_id = :expertId", nativeQuery = true)
    List<Order> findAllByExpert(@Param("expertId") Integer expertId);

    List<Order> findAllByStatus(OrderStatus status);
}
