package com.gabriel.salesservice.service;

import com.gabriel.salesservice.dto.SaleDTO;

public interface ISaleService {

    public void createSale(Long idCart);

    public SaleDTO getSale(Long id);
}
