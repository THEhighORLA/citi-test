package com.tax.transactions.citi_test.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.entities.TransactionEntity;
import com.tax.transactions.citi_test.repositories.TaxRepository;
import com.tax.transactions.citi_test.repositories.TransactionRepository;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTransactionById() {
        TransactionEntity mockTransaction = new TransactionEntity();
        mockTransaction.setId(1L);
        mockTransaction.setAmount(1000.0);

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(mockTransaction));

        TransactionEntity transaction = transactionService.getTransactionById(1L);

        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
        assertEquals(1000.0, transaction.getAmount());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveTransaction() {
        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setAmount(500.0);
        newTransaction.setId(1L);

        TaxEntity tax = new TaxEntity();
        
        tax.setId(1L);
        tax.setTaxRate(0.1);
        tax.setDescription("IVA");

        newTransaction.setTaxEntity(tax);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));

        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(newTransaction);

        TransactionEntity savedTransaction = transactionService.saveTransaction(newTransaction);

        assertNotNull(savedTransaction);
        assertEquals(500.0, savedTransaction.getAmount());
        verify(transactionRepository, times(1)).save(newTransaction);
    }
}
