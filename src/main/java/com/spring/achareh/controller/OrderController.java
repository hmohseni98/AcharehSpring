package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.User;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.dto.order.OrderCustomerDTO;
import com.spring.achareh.service.dto.order.OrderExpertDTO;
import com.spring.achareh.service.dto.order.OrderRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    ModelMapper modelMapper;
    OrderService orderService;

    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void orderRegister(@RequestBody OrderRegisterDTO orderDTO) {
        Customer customer = new Customer();
        Speciality speciality = new Speciality();
        Order order = new Order();
        customer.setId(orderDTO.getCustomerId());
        speciality.setId(orderDTO.getSpecialityId());
        order.setCustomer(customer);
        order.setSpeciality(speciality);
        order.setDescription(orderDTO.getDescription());
        order.setWorkDate(orderDTO.getWorkDate());
        order.setSuggestionPrice(orderDTO.getSuggestionPrice());
        order.setAddress(orderDTO.getAddress());
        orderService.orderRegister(order);
    }

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderExpertDTO> findAll() {
        return orderService.selectAllByStatus();
    }

    @GetMapping("/find-all-by-expert")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderExpertDTO> findAllByExpert(HttpServletRequest request) {
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
        return orderService.selectAllByExpert(user.getId());
    }

    @GetMapping("/find-all-by-customer")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderCustomerDTO> selectAllByCustomer(HttpServletRequest request){
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
        return orderService.selectAllByCustomer(user.getId());
    }
}
