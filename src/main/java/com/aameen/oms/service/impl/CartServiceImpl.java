package com.aameen.oms.service.impl;

import com.aameen.oms.dto.CartDTO;
import com.aameen.oms.dto.CartItemDTO;
import com.aameen.oms.entity.Cart;
import com.aameen.oms.entity.CartItem;
import com.aameen.oms.entity.Product;
import com.aameen.oms.entity.User;
import com.aameen.oms.exception.BadRequestException;
import com.aameen.oms.exception.ResourceNotFoundException;
import com.aameen.oms.repository.CartRepository;
import com.aameen.oms.repository.ProductRepository;
import com.aameen.oms.repository.UserRepository;
import com.aameen.oms.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    @Override
    public CartDTO addToCart(Long productId, Integer quantity) {

        if (quantity == null || quantity < 1) {
            throw new BadRequestException("Quantity must be at least 1");
        }

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));


        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
            cart.getItems().add(item);
        }

        cartRepository.save(cart);

        return mapToDTO(cart);
    }

    @Override
    public CartDTO getCart() {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        return mapToDTO(cart);
    }

    @Override
    public void removeFromCart(Long productId) {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        boolean removed = cart.getItems().removeIf(item ->
                item.getProduct().getId().equals(productId)
        );

        if (!removed) {
            throw new ResourceNotFoundException("Product not found in cart");
        }

        cartRepository.save(cart);
    }
    private CartDTO mapToDTO(Cart cart) {

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());

        dto.setItems(cart.getItems().stream().map(item -> {
            CartItemDTO i = new CartItemDTO();
            i.setProductId(item.getProduct().getId());
            i.setProductName(item.getProduct().getName());
            i.setQuantity(item.getQuantity());
            i.setPrice(item.getProduct().getPrice());
            return i;
        }).collect(Collectors.toList()));

        return dto;
    }

}
