package com.tax.transactions.citi_test.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.repositories.TaxRepository;

@Service
public class TaxServiceImpl implements TaxService {
    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<TaxEntity> getAllTaxes() {
        return this.taxRepository.findAll();
    }

    @Override
    public Optional<TaxEntity> getTaxById(Long id) {
        return this.taxRepository.findById(id);
    }

    @Override
    public TaxEntity saveTax(TaxEntity tax) {
        this.taxRepository.save(tax);
        return tax;
    }

}
