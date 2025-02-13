package com.tax.transactions.citi_test.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.tax.transactions.citi_test.services.TransactionService;

import utils.ResponseMapper;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseMapper<TransactionEntity>> getAllTransactions() {
        List<TransactionEntity> trx = this.transactionService.getAllTransactions();

        if (trx == null || trx.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMapper<>("No transactions found", HttpStatus.NOT_FOUND.value(), "Error", new ArrayList<>()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMapper<>("Transactions retrieved successfully", HttpStatus.OK.value(), "Success", trx));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMapper<TransactionEntity>> getTransactionById(@PathVariable("id") Long id) {
        TransactionEntity trx = this.transactionService.getTransactionById(id).orElse(null);

        if (trx == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMapper<>("Transaction with id " + id + " not found", HttpStatus.NOT_FOUND.value(), "Error", new ArrayList<>()));
        }

        List<TransactionEntity> trxAsObjects = new ArrayList<>();
        trxAsObjects.add(trx);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMapper<>("Transaction retrieved successfully", HttpStatus.OK.value(), "Success", trxAsObjects));
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMapper<TransactionEntity>> saveTransaction(@RequestBody TransactionEntity transaction) {
        TransactionEntity trx = this.transactionService.saveTransaction(transaction);

        if (trx == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMapper<>("Error saving transaction", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", new ArrayList<>()));
        }

        List<TransactionEntity> trxAsObjects = new ArrayList<>();
        trxAsObjects.add(trx);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMapper<>("Transaction saved successfully", HttpStatus.CREATED.value(), "Success", trxAsObjects));
    }

}
