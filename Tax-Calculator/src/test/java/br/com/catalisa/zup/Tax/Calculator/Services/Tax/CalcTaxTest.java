package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CalcTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CalcTax calcTax;

    @Test
    void testCalculateTax_Success() {
        // Arrange
        Long taxId = 1L;
        double baseValue = 100.0;
        Tax tax = new Tax(taxId, "VAT", "Value Added Tax", 10.0);
        Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.of(tax));

        // Act
        CalcTaxDTO result = calcTax.calculateTax(taxId, baseValue);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("VAT", result.getName());
        Assertions.assertEquals(10.0, result.getRate());
        Assertions.assertEquals(10.0, result.getTaxValue());
    }

    @Test
    void testCalculateTax_TaxNotFound() {
        // Arrange
        Long taxId = 1L;
        double baseValue = 100.0;
        Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calcTax.calculateTax(taxId, baseValue);
        });
        Assertions.assertEquals("Tax with ID 1 not found.", exception.getMessage());
    }
}