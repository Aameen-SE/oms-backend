package com.aameen.oms.repository;

import com.aameen.oms.entity.Order;
import com.aameen.oms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
}
