package com.aameen.oms.controller;

import com.aameen.oms.dto.CartDTO;
import com.aameen.oms.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartDTO addToCart(@RequestParam Long productId,
                             @RequestParam Integer quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @GetMapping
    public CartDTO getCart() {
        return cartService.getCart();
    }

    @DeleteMapping("/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
    }
}
