package com.spring.achareh.service.impl;

import com.spring.achareh.model.Customer;
import com.spring.achareh.repository.CustomerRepository;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer, CustomerRepository>
        implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
