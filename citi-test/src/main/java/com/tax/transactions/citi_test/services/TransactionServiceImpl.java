package com.tax.transactions.citi_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.entities.TransactionEntity;
import com.tax.transactions.citi_test.repositories.TaxRepository;
import com.tax.transactions.citi_test.repositories.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity getTransactionById(Long id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Transaction with ID " + id + " not found"));
    }

    @Override
    @Transactional
    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        if (transaction.getTaxEntity() == null || transaction.getTaxEntity().getId() == null) {
            throw new IllegalArgumentException("Transaction must have a valid Tax ID");
        }

        // Buscar el impuesto asociado
        TaxEntity tax = taxRepository.findById(transaction.getTaxEntity().getId())
            .orElseThrow(() -> new EntityNotFoundException("Tax with ID " + transaction.getTaxEntity().getId() + " not found"));

        // Calcular el impuesto
        Double calculatedTax = transaction.getAmount() * tax.getTaxRate();
        transaction.setCalculatedTax(calculatedTax);

        transaction.setTaxEntity(tax);

        return transactionRepository.save(transaction);
    }
}
