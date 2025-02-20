package com.tax.transactions.citi_test.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tax.transactions.citi_test.entities.TransactionEntity;
import com.tax.transactions.citi_test.errors.exceptions.ResourceNotFoundException;
import com.tax.transactions.citi_test.services.TransactionService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import utils.ResponseMapper;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControllerImpl {

    @Autowired
    private TransactionService transactionService;

    private static final String TRANSACTION_SERVICE = "transactionService";

    @GetMapping
    @CircuitBreaker(name = TRANSACTION_SERVICE, fallbackMethod = "fallbackGetAllTransactions")
    @Retry(name = TRANSACTION_SERVICE)
    @TimeLimiter(name = TRANSACTION_SERVICE)
    public CompletableFuture<ResponseEntity<ResponseMapper<List<TransactionEntity>>>> getAllTransactions() {
        return CompletableFuture.supplyAsync(() -> {
            List<TransactionEntity> transactions = transactionService.getAllTransactions();

            if (transactions.isEmpty()) {
                throw new ResourceNotFoundException("No transactions found");
            }

            return ResponseEntity.ok(
                    new ResponseMapper<>("Transactions retrieved successfully", HttpStatus.OK.value(), "Success", transactions)
            );
        });
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = TRANSACTION_SERVICE, fallbackMethod = "fallbackGetTransactionById")
    @Retry(name = TRANSACTION_SERVICE)
    @TimeLimiter(name = TRANSACTION_SERVICE)
    public CompletableFuture<ResponseEntity<ResponseMapper<TransactionEntity>>> getTransactionById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            TransactionEntity transaction = transactionService.getTransactionById(id);

            if (transaction == null) throw new ResourceNotFoundException("Transaction with id " + id + " not found");

            return ResponseEntity.ok(
                    new ResponseMapper<>("Transaction retrieved successfully", HttpStatus.OK.value(), "Success", transaction)
            );
        });
    }

    @PostMapping
    @CircuitBreaker(name = TRANSACTION_SERVICE, fallbackMethod = "fallbackSaveTransaction")
    @Retry(name = TRANSACTION_SERVICE)
    @TimeLimiter(name = TRANSACTION_SERVICE)
    public CompletableFuture<ResponseEntity<ResponseMapper<TransactionEntity>>> saveTransaction(@RequestBody TransactionEntity transaction) {
        return CompletableFuture.supplyAsync(() -> {
            TransactionEntity savedTransaction = transactionService.saveTransaction(transaction);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMapper<>("Transaction saved successfully", HttpStatus.CREATED.value(), "Success", savedTransaction));
        });
    }


    
    public ResponseEntity<ResponseMapper<List<TransactionEntity>>> fallbackGetAllTransactions(Exception e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseMapper<>("Transaction service is temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE.value(), "Failure", null));
    }

    public ResponseEntity<ResponseMapper<TransactionEntity>> fallbackGetTransactionById(Long id, Exception e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseMapper<>("Transaction service is temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE.value(), "Failure", null));
    }

    public ResponseEntity<ResponseMapper<TransactionEntity>> fallbackSaveTransaction(TransactionEntity transaction, Exception e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseMapper<>("Transaction service is temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE.value(), "Failure", null));
    }
}
