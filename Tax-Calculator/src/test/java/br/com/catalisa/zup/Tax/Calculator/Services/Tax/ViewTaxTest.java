package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ViewTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private ViewTax viewTax;

    @Test
    void testGetAllTaxes() {
        // Arrange
        List<Tax> taxes = List.of(
                new Tax(1L, "VAT", "Value Added Tax", 10.0),
                new Tax(2L, "GST", "Goods and Services Tax", 15.0)
        );
        Mockito.when(taxRepository.findAll()).thenReturn(taxes);

        // Act
        List<TaxDTO> result = viewTax.getAllTaxes();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetTaxById_Success() {
        // Arrange
        Long taxId = 1L;
        Tax tax = new Tax(taxId, "VAT", "Value Added Tax", 10.0);
        Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.of(tax));

        // Act
        TaxDTO result = viewTax.getTaxById(taxId);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("VAT", result.getName());
    }

    @Test
    void testGetTaxById_TaxNotFound() {
        // Arrange
        Long taxId = 1L;
        Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            viewTax.getTaxById(taxId);
        });
        Assertions.assertEquals("Tax not find", exception.getMessage());
    }
}