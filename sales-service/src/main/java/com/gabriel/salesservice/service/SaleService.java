package com.gabriel.salesservice.service;

import com.gabriel.salesservice.dto.CartDTO;
import com.gabriel.salesservice.dto.SaleDTO;
import com.gabriel.salesservice.model.Sale;
import com.gabriel.salesservice.repository.ICartAPIClient;
import com.gabriel.salesservice.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private ICartAPIClient cartAPIClient;

    @Override
    public void createSale(Long idCart) {
        CartDTO cartDTO = cartAPIClient.getCart(idCart);
        Sale sale = new Sale();
        sale.setCartId(cartDTO.getId());
        sale.setDate(LocalDate.now());
        saleRepository.save(sale);
    }

    @Override
    @CircuitBreaker(name = "cart-service",fallbackMethod = "fallbackGetProducts")
    @Retry(name = "cart-service")
    public SaleDTO getSale(Long id) {
        Sale sale = saleRepository.findById(id).orElse(null);
        SaleDTO saleDTO = new SaleDTO();
        assert sale != null;
        saleDTO.setId(id);
        saleDTO.setDate(sale.getDate());
        CartDTO cartDTO = cartAPIClient.getCart(sale.getCartId());
        saleDTO.setCart(cartDTO);
        //createException();
        return saleDTO;
    }

    public SaleDTO fallbackGetProducts(Throwable throwable){
        return new SaleDTO(99999L,null,null);
    }

    public void createException(){
        throw new IllegalArgumentException("test circuit breaker");
    }
}
