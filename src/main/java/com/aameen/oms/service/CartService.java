package com.aameen.oms.service;

import com.aameen.oms.dto.CartDTO;

public interface CartService {

    CartDTO addToCart(Long productId, Integer quantity);

    CartDTO getCart();

    void removeFromCart(Long productId);
}
