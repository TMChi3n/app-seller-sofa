package com.sofa.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofa.Backend.model.Cart;
import com.sofa.Backend.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
