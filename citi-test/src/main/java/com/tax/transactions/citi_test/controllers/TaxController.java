package com.tax.transactions.citi_test.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tax.transactions.citi_test.entities.TaxEntity;

import utils.ResponseMapper;

public interface TaxController {

    @GetMapping
    ResponseEntity<ResponseMapper<List<TaxEntity>>> getAllTaxes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseMapper<TaxEntity>> getTaxById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<ResponseMapper<TaxEntity>> saveTax(@RequestBody TaxEntity tax);
}