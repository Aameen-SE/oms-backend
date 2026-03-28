package com.aameen.oms.controller;

import com.aameen.oms.dto.ApiResponse;
import com.aameen.oms.dto.CartDTO;
import com.aameen.oms.service.CartService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ApiResponse<CartDTO> addToCart(
            @RequestParam Long productId,
            @RequestParam @NotNull @Min(1) Integer quantity) {

        CartDTO cart = cartService.addToCart(productId, quantity);

        return ApiResponse.<CartDTO>builder()
                .success(true)
                .message("Item added to cart")
                .data(cart)
                .build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ApiResponse<CartDTO> getCart() {

        CartDTO cart = cartService.getCart();

        return ApiResponse.<CartDTO>builder()
                .success(true)
                .message("Cart fetched successfully")
                .data(cart)
                .build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/{productId}")
    public ApiResponse<Object> removeFromCart(@PathVariable Long productId) {

        cartService.removeFromCart(productId);

        return ApiResponse.builder()
                .success(true)
                .message("Item removed from cart")
                .data(null)
                .build();
    }
}
