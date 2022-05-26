package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Order;
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
    public ResponseEntity<Order> orderRegister(@Valid @ModelAttribute OrderRegisterDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderService.orderRegister(order);
        if (order.getId() != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
