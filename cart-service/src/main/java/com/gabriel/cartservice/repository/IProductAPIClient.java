package com.gabriel.cartservice.repository;

import com.gabriel.cartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "product-service")
public interface IProductAPIClient {

    @GetMapping("/product/{ids}")
    public List<ProductDTO> findProducts(@PathVariable ("ids") List<Long> ids);
}
