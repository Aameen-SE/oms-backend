package com.aameen.oms.controller;


import com.aameen.oms.dto.ApiResponse;
import com.aameen.oms.dto.OrderDTO;
import com.aameen.oms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;

    @GetMapping("/orders")
    public ApiResponse<List<OrderDTO>> getAllOrders() {

        List<OrderDTO> orders = adminService.getAllOrders();

        return ApiResponse.<List<OrderDTO>>builder()
                .success(true)
                .message("Orders fetched successfully")
                .data(orders)
                .build();
    }

    @PutMapping("/orders/{id}/status")
    public ApiResponse<OrderDTO> updateOrderStatus(@PathVariable Long id,
                                                   @RequestParam String status) {

        OrderDTO updated = adminService.updateOrderStatus(id, status);

        return ApiResponse.<OrderDTO>builder()
                .success(true)
                .message("Order status updated")
                .data(updated)
                .build();
    }
}
