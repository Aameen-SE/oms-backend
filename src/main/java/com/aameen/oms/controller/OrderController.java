package com.aameen.oms.controller;


import com.aameen.oms.dto.ApiResponse;
import com.aameen.oms.dto.OrderDTO;
import com.aameen.oms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ApiResponse<OrderDTO> createOrder() {

        OrderDTO order = orderService.createOrder();

        return ApiResponse.<OrderDTO>builder()
                .success(true)
                .message("Order placed successfully")
                .data(order)
                .build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ApiResponse<List<OrderDTO>> getOrders() {

        List<OrderDTO> orders = orderService.getUserOrders();

        return ApiResponse.<List<OrderDTO>>builder()
                .success(true)
                .message("Orders fetched successfully")
                .data(orders)
                .build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ApiResponse<OrderDTO> getOrder(@PathVariable Long id) {

        OrderDTO order = orderService.getOrderById(id);

        return ApiResponse.<OrderDTO>builder()
                .success(true)
                .message("Order fetched successfully")
                .data(order)
                .build();
    }


}
