package com.spring.achareh.repository;


import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.service.dto.order.OrderCustomerDTO;
import com.spring.achareh.service.dto.order.OrderExpertDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllyByCustomer(Customer customer);

    List<Order> findAllBySpeciality(Speciality speciality);

    List<Order> findAllByStatus(OrderStatus status);

    @Query("select new com.spring.achareh.service.dto.order.OrderExpertDTO(o.id , " +
            "s.name as serviceName , " +
            "concat(c.firstName, ' ',c.lastName) as customerFullName, " +
            "o.description , " +
            "o.suggestionPrice , " +
            "o.workDate ) " +
            "from com.spring.achareh.model.Order o " +
            "join o.customer c " +
            "join o.speciality s " +
            "where o.status = ?1")
    List<OrderExpertDTO> selectAllByStatus(OrderStatus status);

    @Query("select new com.spring.achareh.service.dto.order.OrderExpertDTO(o.id , " +
            "s.name as serviceName , " +
            "concat(c.firstName, ' ',c.lastName) as customerFullName, " +
            "o.description , " +
            "o.suggestionPrice , " +
            "o.workDate ) " +
            "from com.spring.achareh.model.Order o " +
            "join o.customer c " +
            "join o.speciality s " +
            "join o.acceptOffer f " +
            "join f.expert e " +
            "where e.id = ?1")
    List<OrderExpertDTO> selectAllByExpert(Integer expertId);

    @Query("select new com.spring.achareh.service.dto.order.OrderCustomerDTO(o.id , " +
            "s.name as serviceName, " +
            "concat(e.firstName, ' ',e.lastName) as expertFullName, " +
            "o.description , " +
            "o.suggestionPrice , " +
            "o.status , " +
            "o.submitDateTime as submitDate , " +
            "o.workDate ) " +
            "from com.spring.achareh.model.Order o " +
            "join o.customer c " +
            "join o.speciality s " +
            "join o.acceptOffer f " +
            "join f.expert e " +
            "where c.id = ?1")
    List<OrderCustomerDTO> selectAllByCustomer(Integer customerId);


}
