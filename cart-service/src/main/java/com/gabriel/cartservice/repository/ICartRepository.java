package com.gabriel.cartservice.repository;

import com.gabriel.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
}
