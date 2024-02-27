package com.gabriel.cartservice.service;

import com.gabriel.cartservice.dto.CartDTO;
import com.gabriel.cartservice.dto.ProductDTO;
import com.gabriel.cartservice.model.Cart;
import com.gabriel.cartservice.repository.ICartRepository;
import com.gabriel.cartservice.repository.IProductAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductAPIClient productAPIClient;


    @Override
    public void createCart(List<Long> productsId) {
        Cart cart = new Cart();
        cart.setProductsId(productsId);
        List<ProductDTO> list = productAPIClient.findProducts(productsId);
        Double contador = 0D;
        for(ProductDTO product : list){
            contador = product.getPrice() + contador;
        }
        cart.setTotalPrice(contador);
        cartRepository.save(cart);
    }

    @Override
    @CircuitBreaker(name = "product-service",fallbackMethod = "fallbackGetProducts")
    @Retry(name = "product-service")
    public CartDTO getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        CartDTO cartDTO =new CartDTO();
        assert cart != null;
        cartDTO.setId(id);
        List<ProductDTO> products = productAPIClient.findProducts(cart.getProductsId());
        cartDTO.setListProducts(products);
        cartDTO.setTotalPrice(cart.getTotalPrice());
        //createException();
        return cartDTO;
    }

    public CartDTO fallbackGetProducts(Throwable throwable){
        return new CartDTO(99999L,0D,null);
    }

    public void createException(){
        throw new IllegalArgumentException("test circuit breaker");
    }
}
