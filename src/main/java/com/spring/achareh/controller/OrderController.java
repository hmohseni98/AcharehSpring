package com.spring.achareh.controller;

import com.spring.achareh.model.Order;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.dto.order.OrderDTO;
import com.spring.achareh.service.dto.order.OrderRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Order> orderRegister(@Valid @ModelAttribute OrderRegisterDTO orderDTO){
        Order order = modelMapper.map(orderDTO,Order.class);
        orderService.orderRegister(order);
        if (order.getId() != null){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> findAll(){
        return orderService.selectAllByStatus();
    }
}
