package com.tax.transactions.citi_test.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TaxEntity;

@Service
public interface TaxService {

    TaxEntity saveTax(TaxEntity tax);

    List<TaxEntity> getAllTaxes();

    Optional<TaxEntity> getTaxById(Long id);
}
