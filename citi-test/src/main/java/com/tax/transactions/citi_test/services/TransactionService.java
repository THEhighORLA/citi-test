package com.tax.transactions.citi_test.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tax.transactions.citi_test.entities.TransactionEntity;

@Service
public interface TransactionService {
    
    TransactionEntity saveTransaction(TransactionEntity transaction);

    List<TransactionEntity> getAllTransactions();
    
    TransactionEntity getTransactionById(Long id);

}
