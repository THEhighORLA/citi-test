package com.tax.transactions.citi_test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tax.transactions.citi_test.entities.TaxEntity;

@Repository
public interface TaxRepository extends JpaRepository<TaxEntity, Long>{
    
}
