package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.EmailAlreadyExistException;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.repository.CustomerRepository;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer, CustomerRepository>
        implements CustomerService {
    private final UserService userService;

    public CustomerServiceImpl(CustomerRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public void save(Customer customer) {
        if (userService.existsUserByEmail(customer.getEmail()))
            throw new EmailAlreadyExistException();
        customer.setRole(Role.Customer);
        customer.setBalance(0);
        customer.setEnabled(true);
        customer.setExpired(false);
        customer.setCredentialsExpired(false);
        customer.setLocked(false);
        super.save(customer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return repository.findCustomerByEmail(email);
    }
}
