package com.tax.transactions.citi_test.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TransactionEntity;
import com.tax.transactions.citi_test.repositories.TransactionRepository;

import jakarta.transaction.Transactional;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public Optional<TransactionEntity> getTransactionById(Long id) {
        return this.transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        this.transactionRepository.save(transaction);
        
        return transaction;
    }

}
