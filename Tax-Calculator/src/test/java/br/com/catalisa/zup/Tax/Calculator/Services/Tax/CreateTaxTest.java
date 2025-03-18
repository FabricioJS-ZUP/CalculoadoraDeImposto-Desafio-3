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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreateTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CreateTax createTax;

    @Test
    void testAddTax_Success() {
        // Arrange
        TaxDTO taxDTO = new TaxDTO("VAT", "Value Added Tax", 10.0);
        Tax tax = new Tax("VAT", "Value Added Tax", 10.00);
        Tax savedTax = new Tax(1L, "VAT", "Value Added Tax", 10.0);
        Mockito.when(taxRepository.save(Mockito.any(Tax.class))).thenReturn(savedTax);

        // Act
        TaxDTO result = createTax.addTax(taxDTO);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("VAT", result.getName());
    }
}