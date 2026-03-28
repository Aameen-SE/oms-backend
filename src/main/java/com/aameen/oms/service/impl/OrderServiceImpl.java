package com.aameen.oms.service.impl;

import com.aameen.oms.dto.OrderDTO;
import com.aameen.oms.dto.OrderItemDTO;
import com.aameen.oms.entity.*;
import com.aameen.oms.exception.BadRequestException;
import com.aameen.oms.exception.ResourceNotFoundException;
import com.aameen.oms.repository.CartRepository;
import com.aameen.oms.repository.OrderRepository;
import com.aameen.oms.repository.UserRepository;
import com.aameen.oms.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public OrderDTO createOrder() {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {

            Product product = cartItem.getProduct();

            // 🔥 Check stock
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new BadRequestException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            // 🔥 Reduce stock
            product.setStockQuantity(
                    product.getStockQuantity() - cartItem.getQuantity()
            );

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(product.getPrice());

            return item;

        }).collect(Collectors.toList());

        order.setItems(orderItems);

        BigDecimal total = orderItems.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);


        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getUserOrders() {

        User user = getCurrentUser();

        return orderRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {

        User user = getCurrentUser();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You are not allowed to view this order");
        }

        return mapToDTO(order);
    }

    private OrderDTO mapToDTO(Order order) {

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus().name());

        dto.setItems(order.getItems().stream().map(item -> {
            OrderItemDTO i = new OrderItemDTO();
            i.setProductId(item.getProduct().getId());
            i.setProductName(item.getProduct().getName());
            i.setQuantity(item.getQuantity());
            i.setPrice(item.getPrice());
            return i;
        }).collect(Collectors.toList()));

        return dto;
    }


}
