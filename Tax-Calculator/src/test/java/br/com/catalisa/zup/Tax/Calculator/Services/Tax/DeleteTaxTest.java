package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

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
class DeleteTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private DeleteTax deleteTax;

    @Test
    void testDeleteTax_Success() {
        // Arrange
        Long taxId = 1L;
        Mockito.when(taxRepository.existsById(taxId)).thenReturn(true);

        // Act
        deleteTax.deleteTax(taxId);

        // Assert
        Mockito.verify(taxRepository, Mockito.times(1)).deleteById(taxId);
    }

    @Test
    void testDeleteTax_TaxNotFound() {
        // Arrange
        Long taxId = 1L;
        Mockito.when(taxRepository.existsById(taxId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deleteTax.deleteTax(taxId);
        });
        Assertions.assertEquals("Tax not find", exception.getMessage());
    }
}