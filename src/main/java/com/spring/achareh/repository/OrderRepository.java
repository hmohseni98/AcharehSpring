package com.spring.achareh.repository;


import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.service.dto.order.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllBySpeciality(Speciality speciality);

    List<Order> findAllByStatus(OrderStatus status);

    @Query("select new com.spring.achareh.service.dto.order.OrderDTO(o.id , " +
            "s.name , " +
            "concat(c.firstName, ' ',c.lastName) as customerFullName, " +
            "o.description , " +
            "o.suggestionPrice , " +
            "o.workDate ) " +
            "from com.spring.achareh.model.Order o " +
            "join o.customer c " +
            "join o.speciality s " +
            "where o.status = ?1")
    List<OrderDTO> selectAllByStatus(OrderStatus status);

//    List<Order> selectAllByExpert(Integer expertId);
}
