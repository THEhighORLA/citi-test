package com.tax.transactions.citi_test.controllers;

import org.springframework.http.ResponseEntity;

import com.tax.transactions.citi_test.entities.TransactionEntity;

import utils.ResponseMapper;


public interface TransactionController {

    ResponseEntity<ResponseMapper<TransactionEntity>> saveTransaction(TransactionEntity transaction);

    ResponseEntity<ResponseMapper<TransactionEntity>> getAllTransactions();
    
    ResponseEntity<ResponseMapper<TransactionEntity>> getTransactionById(Long id);
}
