package com.tax.transactions.citi_test.controllers;

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

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.services.TaxService;

import utils.ResponseMapper;

@RestController
@RequestMapping("/api/taxes")
public class TaxControllerImpl {

    @Autowired
    private TaxService taxService;

    private static final String TAX_SERVICE = "taxService";

    // Obtener todos los impuestos
    @GetMapping
    public ResponseEntity<ResponseMapper<List<TaxEntity>>> getAllTaxes() {
        List<TaxEntity> taxes = taxService.getAllTaxes();

        if (taxes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseMapper<>("No taxes found", HttpStatus.NO_CONTENT.value(), "Failure", null));
        }

        return ResponseEntity.ok(
                new ResponseMapper<>("Taxes retrieved successfully", HttpStatus.OK.value(), "Success", taxes)
        );
    }

    // Obtener un impuesto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMapper<TaxEntity>> getTaxById(@PathVariable Long id) {
        TaxEntity tax = taxService.getTaxById(id);

        if (tax == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMapper<>("Tax not found", HttpStatus.NOT_FOUND.value(), "Failure", null));
        }

        return ResponseEntity.ok(
                new ResponseMapper<>("Tax retrieved successfully", HttpStatus.OK.value(), "Success", tax)
        );
    }

    // Guardar un nuevo impuesto
    @PostMapping
    public ResponseEntity<ResponseMapper<TaxEntity>> saveTax(@RequestBody TaxEntity tax) {
        TaxEntity savedTax = taxService.saveTax(tax);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMapper<>("Tax saved successfully", HttpStatus.CREATED.value(), "Success", savedTax));
    }

}
