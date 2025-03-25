package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for CreateTax Service")
@ActiveProfiles("test")
class CreateTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CreateTax createTax;

    @Nested
    @DisplayName("Positive scenarios")
    class PositiveScenarios {

        @Test
        @DisplayName("You should add a tax successfully")
        void shouldAddTaxSuccessfully() {
            // Arrange
            TaxDTO taxDTO = new TaxDTO(null, "Tax Name", "Tax Description", 10.0);
            Tax tax = new Tax("Tax Name", "Tax Description", 10.0);
            Tax savedTax = new Tax(1L, "Tax Name", "Tax Description", 10.0);

            Mockito.when(taxRepository.save(Mockito.any(Tax.class))).thenReturn(savedTax);

            // Act
            TaxDTO result = createTax.addTax(taxDTO);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(1L, result.getId());
            Assertions.assertEquals("Tax Name", result.getName());
            Assertions.assertEquals("Tax Description", result.getDescription());
            Assertions.assertEquals(10.0, result.getRate());
            Mockito.verify(taxRepository, Mockito.times(1)).save(Mockito.any(Tax.class));
        }
    }

    @Nested
    @DisplayName("Negative Scenarios")
    class NegativeScenarios {

        @Test
        @DisplayName("Should throw exception when trying to save a null tax")
        void shouldThrowExceptionWhenSavingNullTax() {
            // Arrange
            TaxDTO taxDTO = null;

            // Act & Assert
            Assertions.assertThrows(NullPointerException.class, () -> createTax.addTax(taxDTO));
            Mockito.verifyNoInteractions(taxRepository);
        }
    }
}