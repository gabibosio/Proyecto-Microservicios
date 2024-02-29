package com.gabriel.cartservice.controller;

import com.gabriel.cartservice.dto.CartDTO;
import com.gabriel.cartservice.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping("/create/{ids}")
    public String create(@PathVariable List<Long> ids){
        cartService.createCart(ids);
        return "cart created";
    }

    @GetMapping("/{id}")
    public CartDTO getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PutMapping("/remove/{cartId}/{productId}")
    public String removeProduct(@PathVariable Long cartId,@PathVariable Long productId){
        cartService.deleteProductFromCart(cartId,productId);
        return "product removed";
    }

    @PutMapping("/add/{cartId}/{productId}")
    public String addProduct(@PathVariable Long cartId,@PathVariable Long productId){
        cartService.addProductToCart(cartId,productId);
        return "added product";
    }
}
