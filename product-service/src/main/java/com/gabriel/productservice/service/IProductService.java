package com.gabriel.productservice.service;

import com.gabriel.productservice.model.Product;

import java.util.List;

public interface IProductService {

    public void create(Product product);

    public List<Product> getProducts(List<Long> ids);

    public Product getProduct(Long id);
}
