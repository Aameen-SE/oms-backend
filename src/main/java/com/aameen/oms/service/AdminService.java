package com.aameen.oms.service;

import com.aameen.oms.dto.OrderDTO;

import java.util.List;

public interface AdminService {

    List<OrderDTO> getAllOrders();

    OrderDTO updateOrderStatus(Long orderId, String status);
}
