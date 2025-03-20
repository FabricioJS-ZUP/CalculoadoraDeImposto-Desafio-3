package br.com.catalisa.zup.Tax.Calculator.Services.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.DeleteTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DeleteTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private DeleteTax deleteTax;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteTaxSuccess() {
        // Arrange
        Long taxId = 1L;
        when(taxRepository.existsById(taxId)).thenReturn(true);

        // Act
        deleteTax.deleteTax(taxId);

        // Assert
        verify(taxRepository, times(1)).existsById(taxId);
        verify(taxRepository, times(1)).deleteById(taxId);
    }

    @Test
    void testDeleteTaxNotFound() {
        // Arrange
        Long taxId = 1L;
        when(taxRepository.existsById(taxId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deleteTax.deleteTax(taxId));
        assertEquals("Tax not find", exception.getMessage());

        verify(taxRepository, times(1)).existsById(taxId);
        verify(taxRepository, never()).deleteById(anyLong());
    }

    @Test
    void testDeleteTaxWithNullId() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deleteTax.deleteTax(null));
        assertEquals("Tax ID cannot be null", exception.getMessage());

        verify(taxRepository, never()).existsById(any());
        verify(taxRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteTaxWithNonExistentId() {
        // Arrange
        Long taxId = 999L;
        when(taxRepository.existsById(taxId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deleteTax.deleteTax(taxId));
        assertEquals("Tax not find", exception.getMessage());

        verify(taxRepository, times(1)).existsById(taxId);
        verify(taxRepository, never()).deleteById(anyLong());
    }
}