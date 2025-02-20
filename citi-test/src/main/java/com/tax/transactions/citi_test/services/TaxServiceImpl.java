package com.tax.transactions.citi_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.repositories.TaxRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaxServiceImpl implements TaxService {
    
    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<TaxEntity> getAllTaxes() {
        return taxRepository.findAll();
    }

    @Override
    public TaxEntity getTaxById(Long id) {
        return taxRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tax with ID " + id + " not found"));
    }

    @Override
    public TaxEntity saveTax(TaxEntity tax) {
        return taxRepository.save(tax); 
    }
}
