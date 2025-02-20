package com.tax.transactions.citi_test.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "taxes")
public class TaxEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(1)
    @NotNull
    @Positive
    private Double taxRate; // Tax rule as 0.1 for 10%

    @NotNull
    private String description;

    @OneToMany(mappedBy = "taxEntity", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    public TaxEntity() {}

    public TaxEntity(Long id, Double taxRate, String description) {
        this.id = id;
        this.taxRate = taxRate;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getTaxRate() { return taxRate; }
    public void setTaxRate(Double taxRate) { this.taxRate = taxRate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<TransactionEntity> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionEntity> transactions) { this.transactions = transactions; }
}
