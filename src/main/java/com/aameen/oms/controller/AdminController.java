package com.aameen.oms.controller;


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
    public List<OrderDTO> getAllOrders() {
        return adminService.getAllOrders();
    }

    @PutMapping("/orders/{id}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long id,
                                      @RequestParam String status) {
        return adminService.updateOrderStatus(id, status);
    }
}
