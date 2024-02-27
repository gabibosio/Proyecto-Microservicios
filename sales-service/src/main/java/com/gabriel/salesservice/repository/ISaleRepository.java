package com.gabriel.salesservice.repository;

import com.gabriel.salesservice.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale,Long> {
}
