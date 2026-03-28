package com.aameen.oms.controller;


import com.aameen.oms.dto.OrderDTO;
import com.aameen.oms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder() {
        return orderService.createOrder();
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.getUserOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }



}
