package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Admin;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.User;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.dto.customer.CustomerRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/account-balance")
    @ResponseStatus(HttpStatus.OK)
    public int accountBalance(HttpServletRequest request) {
        User user = null;
        if (request.getCookies() == null)
            throw new AccessDeniedException();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sec_data")) {
                user = UserController.userMap.get(cookie.getValue());
                break;
            }
        }
        if (user == null)
            throw new AccessDeniedException();
        Customer customer = customerService.findById(user.getId()).get();
        return customer.getBalance();
    }
}
