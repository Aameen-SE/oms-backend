package com.aameen.oms.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDTO> items;

}