package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.User;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.dto.customer.CustomerRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@Valid @RequestBody CustomerRegisterDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.save(customer);
    }

    @GetMapping("/accountBalance")
    @ResponseStatus(HttpStatus.OK)
    public int accountBalance(@RequestParam Integer userId) {
        Customer customer = customerService.findById(userId).get();
        return customer.getBalance();
    }
}
