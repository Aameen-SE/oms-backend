package com.aameen.oms.service;

import com.aameen.oms.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder();

    List<OrderDTO> getUserOrders();

    OrderDTO getOrderById(Long id);

}
