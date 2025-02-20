package com.tax.transactions.citi_test.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    private Double amount;

    @NotNull
    private LocalDate date;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "tax_id")
    private TaxEntity taxEntity;

    @NotNull
    @Min(0)
    private Double calculatedTax; // Guardamos el impuesto calculado para trazabilidad

    public TransactionEntity() {}

    public TransactionEntity(Long id, Double amount, LocalDate date, TaxEntity taxEntity, Double calculatedTax) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.taxEntity = taxEntity;
        this.calculatedTax = calculatedTax;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public TaxEntity getTaxEntity() { return taxEntity; }
    public void setTaxEntity(TaxEntity taxEntity) { this.taxEntity = taxEntity; }

    public Double getCalculatedTax() { return calculatedTax; }
    public void setCalculatedTax(Double calculatedTax) { this.calculatedTax = calculatedTax; }
}
