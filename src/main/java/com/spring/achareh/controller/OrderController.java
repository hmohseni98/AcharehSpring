package com.spring.achareh.controller;

import com.spring.achareh.model.Order;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.dto.order.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/order")
public class OrderController {
    ModelMapper modelMapper;
    OrderService orderService;

    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    public ResponseEntity<Order> orderRegister(@Valid OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO,Order.class);
        orderService.orderRegister(order);
        if (order.getId() != null){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
