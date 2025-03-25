package br.com.catalisa.zup.Tax.Calculator.Services.Tax;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.ResourceNotFoundException;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.DeleteTax;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for DeleteTax Service")
@ActiveProfiles("test")
class DeleteTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private DeleteTax deleteTax;

    @Nested
    @DisplayName("Positive Scenarios")
    class PositiveScenarios {

        @Test
        @DisplayName("Must delete a tax successfully")
        void shouldDeleteTaxSuccessfully() {
            // Arrange
            Long taxId = 1L;
            Mockito.when(taxRepository.existsById(taxId)).thenReturn(true);

            // Act
            deleteTax.deleteTax(taxId);

            // Assert
            Mockito.verify(taxRepository, Mockito.times(1)).deleteById(taxId);
        }
    }

    @Nested
    @DisplayName("Negative Scenarios")
    class NegativeScenarios {

        @Test
        @DisplayName("Should throw exception when trying to delete a non-existent tax")
        void shouldThrowExceptionWhenDeletingNonExistentTax() {
            // Arrange
            Long taxId = 1L;
            Mockito.when(taxRepository.existsById(taxId)).thenReturn(false);

            // Act & Assert
            ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> deleteTax.deleteTax(taxId));
            Assertions.assertEquals("Tax not found with ID: " + taxId, exception.getMessage());
            Mockito.verify(taxRepository, Mockito.times(0)).deleteById(taxId);
        }

        @Test
        @DisplayName("Should throw exception when trying to delete with null ID")
        void shouldThrowExceptionWhenDeletingWithNullId() {
            // Act & Assert
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> deleteTax.deleteTax(null));
            Assertions.assertEquals("Tax ID cannot be null", exception.getMessage());
            Mockito.verifyNoInteractions(taxRepository);
        }
    }
}
