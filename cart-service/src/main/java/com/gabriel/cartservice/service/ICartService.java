package com.gabriel.cartservice.service;

import com.gabriel.cartservice.dto.CartDTO;

import java.util.List;

public interface ICartService {

    public void createCart(List<Long> productsId);

    public CartDTO getCart(Long id);
}
