package com.spring.achareh.controller;

import com.spring.achareh.model.Admin;
import com.spring.achareh.model.Customer;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.dto.customer.CustomerRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void save(@Valid @ModelAttribute CustomerRegisterDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerService.save(customer);
    }
}
