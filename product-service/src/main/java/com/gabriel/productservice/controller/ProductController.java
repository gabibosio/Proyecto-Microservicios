package com.gabriel.productservice.controller;

import com.gabriel.productservice.model.Product;
import com.gabriel.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Value("${server.port}")
    private int serverPort;

    @PostMapping("/create")
    public String create(@RequestBody Product product){
        productService.create(product);
        return "product created";
    }

    @GetMapping("/findAll/{ids}")
    public List<Product> getProduct(@PathVariable List<Long> ids){
        System.out.println("Port: " + serverPort);
        return productService.getProducts(ids);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        System.out.println("Port: " + serverPort);
        return productService.getProduct(id);
    }
}
