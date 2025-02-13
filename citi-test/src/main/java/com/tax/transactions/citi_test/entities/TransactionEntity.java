package com.tax.transactions.citi_test.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Double amount;
    
    @NotBlank
    private Date date;

    @ManyToOne
    @JoinColumn(name = "tax_id")
    private TaxEntity taxEntity;
    
    public TaxEntity getTaxEntity() {
        return taxEntity;
    }


    public void setTaxEntity(TaxEntity taxEntity) {
        this.taxEntity = taxEntity;
    }


    public TransactionEntity() {
    }


    public TransactionEntity(Long id, Double amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }




    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Double getAmount() {
        return amount;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    
}
