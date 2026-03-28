package com.aameen.oms.repository;

import com.aameen.oms.entity.Cart;
import com.aameen.oms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUser(User user);

}
