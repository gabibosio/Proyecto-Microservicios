package com.gabriel.salesservice.repository;

import com.gabriel.salesservice.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface ICartAPIClient {

    @GetMapping("/cart/{idCart}")
    public CartDTO getCart(@PathVariable ("idCart") Long id);
}
