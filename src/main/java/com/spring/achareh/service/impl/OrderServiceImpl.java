package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.InvalidDateException;
import com.spring.achareh.exceptionHandler.customException.SuggestionPriceMustBeHigherThanTheBasePriceException;
import com.spring.achareh.model.*;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OrderRepository;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import com.spring.achareh.service.dto.order.OrderCustomerDTO;
import com.spring.achareh.service.dto.order.OrderExpertDTO;
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
    public List<OrderExpertDTO> selectAllByExpert(Integer expertId) {
        return repository.selectAllByExpert(expertId);
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        return repository.findAllByStatus(status);
    }

    @Transactional
    @Override
    public void orderRegister(Order order) {
        Customer customer = customerService.findById(order.getCustomer().getId()).get();
        Speciality speciality = specialityService.findById(order.getSpeciality().getId()).get();
        if (order.getSuggestionPrice() < speciality.getBasePrice())
            throw new SuggestionPriceMustBeHigherThanTheBasePriceException();
        if (order.getWorkDate().isBefore(LocalDate.now()))
            throw new InvalidDateException();
        order.setCustomer(customer);
        order.setSpeciality(speciality);
        order.setStatus(OrderStatus.waitingForExpertSuggestions);
        repository.save(order);
    }

    @Override
    public List<OrderExpertDTO> selectAllByStatus() {
        return repository.selectAllByStatus(OrderStatus.waitingForExpertSuggestions);
    }

    @Override
    public List<OrderCustomerDTO> selectAllByCustomer(Integer customerId) {
        return repository.selectAllByCustomer(customerId);
    }
}
