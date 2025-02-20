package com.tax.transactions.citi_test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tax.transactions.citi_test.entities.TaxEntity;
import com.tax.transactions.citi_test.repositories.TaxRepository;

public class TaxServiceTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TaxServiceImpl taxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaxById() {
        TaxEntity mockTax = new TaxEntity();
        mockTax.setId(1L);
        mockTax.setTaxRate(0.1);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(mockTax));

        TaxEntity tax = taxService.getTaxById(1L);

        assertNotNull(tax);
        assertEquals(1L, tax.getId());
        assertEquals(0.1, tax.getTaxRate());
        verify(taxRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveTax() {
        TaxEntity newTax = new TaxEntity();
        newTax.setTaxRate(0.1);
        newTax.setId(1L);

        when(taxRepository.save(newTax)).thenReturn(newTax);

        TaxEntity tax = taxService.saveTax(newTax);

        assertNotNull(tax);
        assertEquals(1L, tax.getId());
        assertEquals(0.1, tax.getTaxRate());
        verify(taxRepository, times(1)).save(newTax);
    }

}
