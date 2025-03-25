package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.ResourceNotFoundException;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
@DisplayName("tests for ViewTax Service")
@ActiveProfiles("test")
class ViewTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private ViewTax viewTax;

    @Nested
    @DisplayName("Positive Scenarios")
    class PositiveScenarios {

        @Test
        @DisplayName("Must return all taxes successfully")
        void shouldReturnAllTaxesSuccessfully() {
            // Arrange
            List<Tax> taxes = List.of(
                    new Tax(1L, "Tax1", "Description1", 10.0),
                    new Tax(2L, "Tax2", "Description2", 20.0)
            );
            Mockito.when(taxRepository.findAll()).thenReturn(taxes);

            // Act
            List<TaxDTO> result = viewTax.getAllTaxes();

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(2, result.size());
            Assertions.assertEquals("Tax1", result.get(0).getName());
            Assertions.assertEquals("Tax2", result.get(1).getName());
            Mockito.verify(taxRepository, Mockito.times(1)).findAll();
        }

        @Test
        @DisplayName("Must return a tax by ID successfully")
        void shouldReturnTaxByIdSuccessfully() {
            // Arrange
            Long taxId = 1L;
            Tax tax = new Tax(taxId, "Tax1", "Description1", 10.0);
            Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.of(tax));

            // Act
            TaxDTO result = viewTax.getTaxById(taxId);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(taxId, result.getId());
            Assertions.assertEquals("Tax1", result.getName());
            Assertions.assertEquals("Description1", result.getDescription());
            Assertions.assertEquals(10.0, result.getRate());
            Mockito.verify(taxRepository, Mockito.times(1)).findById(taxId);
        }
    }

    @Nested
    @DisplayName("Negative Scenarios")
    class NegativeScenarios {

        @Test
        @DisplayName("Should throw exception when fetching tax for non-existent ID")
        void shouldThrowExceptionWhenTaxIdNotFound() {
            // Arrange
            Long taxId = 1L;
            Mockito.when(taxRepository.findById(taxId)).thenReturn(Optional.empty());

            // Act & Assert
            ResourceNotFoundException exception = Assertions.assertThrows(
                    ResourceNotFoundException.class,
                    () -> viewTax.getTaxById(taxId)
            );
            Assertions.assertEquals("Tax not found with ID: " + taxId, exception.getMessage());
            Mockito.verify(taxRepository, Mockito.times(1)).findById(taxId);
        }

        @Test
        @DisplayName("Should return empty list when there are no taxes")
        void shouldReturnEmptyListWhenNoTaxesExist() {
            // Arrange
            Mockito.when(taxRepository.findAll()).thenReturn(Collections.emptyList());

            // Act
            List<TaxDTO> result = viewTax.getAllTaxes();

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertTrue(result.isEmpty());
            Mockito.verify(taxRepository, Mockito.times(1)).findAll();
        }
    }
}