package com.gabriel.productservice.service;

import com.gabriel.productservice.model.Product;
import com.gabriel.productservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void create(Product product) {
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setCode(product.getCode());
        product1.setBrand(product.getBrand());
        product1.setPrice(product.getPrice());
        productRepository.save(product1);
    }

    @Override
    public List<Product> getProducts(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}
