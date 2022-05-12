package com.spring.achareh.service.impl;

import com.spring.achareh.customException.InvalidDate;
import com.spring.achareh.customException.SuggestionPriceMustBeHigherThanTheBasePrice;
import com.spring.achareh.model.*;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OrderRepository;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer, OrderRepository>
        implements OrderService {

    private final SpecialityService specialityService;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository repository, SpecialityService specialityService, CustomerService customerService) {
        super(repository);
        this.specialityService = specialityService;
        this.customerService = customerService;
    }


    @Override
    public List<Order> findAllyByCustomer(Customer customer) {
        return repository.findAllyByCustomer(customer);
    }

    @Override
    public List<Order> findAllBySpeciality(Speciality speciality) {
        return repository.findAllBySpeciality(speciality);
    }

    @Override
    public List<Order> findAllByExpert(Integer expertId) {
        return repository.findAllByExpert(expertId);
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        return repository.findAllByStatus(status);
    }

    @Transactional
    @Override
    public void registerOrder(Integer customer_id, Integer speciality_id, Integer suggestionPrice,
                              String description, LocalDate workDate, String address) {
        Order order = new Order();
        try {
            Speciality speciality = specialityService.findById(speciality_id).get();
            if (suggestionPrice < speciality.getBasePrice()) {
                throw new SuggestionPriceMustBeHigherThanTheBasePrice();
            }
            if (workDate.isBefore(LocalDate.now())) {
                throw new InvalidDate();
            }
            order.setCustomer(customerService.findById(customer_id).get());
            order.setSpeciality(speciality);
            order.setSuggestionPrice(suggestionPrice);
            order.setDescription(description);
            order.setWorkDate(workDate);
            order.setAddress(address);
            order.setStatus(OrderStatus.waitingForExpertSuggestions);
            repository.save(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
