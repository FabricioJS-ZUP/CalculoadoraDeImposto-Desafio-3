package br.com.catalisa.zup.Tax.Calculator.Services.Tax;
import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.CalcTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CalcTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CalcTax calcTax;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTaxSuccess() {
        // Arrange
        Long taxId = 1L;
        double baseValue = 1000.0;
        Tax tax = new Tax();
        tax.setId(taxId);
        tax.setName("ICMS");
        tax.setDescription("Tax on Transactions");
        tax.setRate(18.0);

        when(taxRepository.findById(taxId)).thenReturn(Optional.of(tax));

        // Act
        CalcTaxDTO result = calcTax.calculateTax(taxId, baseValue);

        // Assert
        assertNotNull(result);
        assertEquals("ICMS", result.getName());
        assertEquals("Tax on Transactions", result.getDescription());
        assertEquals(18.0, result.getRate());
        assertEquals(baseValue, result.getBaseValue());
        assertEquals(180.0, result.getTaxValue()); // 18% of 1000.0
        verify(taxRepository, times(1)).findById(taxId);
    }

    @Test
    void testCalculateTaxNotFound() {
        // Arrange
        Long taxId = 1L;
        double baseValue = 1000.0;

        when(taxRepository.findById(taxId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calcTax.calculateTax(taxId, baseValue));
        assertEquals("Tax with ID " + taxId + " not found.", exception.getMessage());
        verify(taxRepository, times(1)).findById(taxId);
    }
}