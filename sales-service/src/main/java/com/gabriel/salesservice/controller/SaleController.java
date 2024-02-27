package com.gabriel.salesservice.controller;

import com.gabriel.salesservice.dto.SaleDTO;
import com.gabriel.salesservice.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @PostMapping("/create/{idCart}")
    public String create (@PathVariable Long idCart){
        saleService.createSale(idCart);
        return "Sale created";
    }

    @GetMapping("/{id}")
    public SaleDTO getSale(@PathVariable Long id){
        return saleService.getSale(id);
    }
}
