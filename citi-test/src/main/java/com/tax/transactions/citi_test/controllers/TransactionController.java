package com.tax.transactions.citi_test.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import com.tax.transactions.citi_test.entities.TransactionEntity;

import utils.ResponseMapper;


public interface TransactionController {

    CompletableFuture<ResponseEntity<ResponseMapper<TransactionEntity>>> saveTransaction(TransactionEntity transaction);

    CompletableFuture<ResponseEntity<ResponseMapper<List<TransactionEntity>>>> getAllTransactions();
    
    CompletableFuture<ResponseEntity<ResponseMapper<TransactionEntity>>> getTransactionById(Long id);
}
