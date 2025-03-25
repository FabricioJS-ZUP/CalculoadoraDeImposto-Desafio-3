package br.com.catalisa.zup.Tax.Calculator.Services.Tax;
import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for CalcTax Service")
@ActiveProfiles("test")
class CalcTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CalcTax calcTax;

    @Nested
    @DisplayName("Positive Scenarios")
    class PositiveScenarios {

        @Test
        @DisplayName("It should calculate the tax successfully")
        void shouldCalculateTaxSuccessfully() {
            // Arrange
            Long taxId = 1L;
            double baseValue = 100.0;
            Tax tax = new Tax(taxId, "Tax Name", "Tax Description", 10.0);

            Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.of(tax));

            // Act
            CalcTaxDTO result = calcTax.calculateTax(taxId, baseValue);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals("Tax Name", result.getName());
            Assertions.assertEquals(100.0, result.getBaseValue());
            Assertions.assertEquals(10.0, result.getRate());
            Assertions.assertEquals(10.0, result.getTaxValue());
            Mockito.verify(taxRepository, Mockito.times(1)).findById(taxId);
        }
    }

    @Nested
    @DisplayName("Negative Scenarios")
    class NegativeScenarios {

        @Test
        @DisplayName("Should throw exception when calculating tax with non-existent ID")
        void shouldThrowExceptionWhenTaxIdNotFound() {
            // Arrange
            Long taxId = 1L;
            double baseValue = 100.0;

            Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.empty());

            // Act & Assert
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> calcTax.calculateTax(taxId, baseValue));
            Assertions.assertEquals("Tax with ID " + taxId + " not found!", exception.getMessage());
            Mockito.verify(taxRepository, Mockito.times(1)).findById(taxId);
        }
    }
}