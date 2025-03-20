package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private CreateTax createTax;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTax() {
        // Arrange
        TaxDTO taxDTO = new TaxDTO(null, "VAT", "Value Added Tax", 15.0);
        Tax tax = new Tax();
        tax.setName(taxDTO.getName());
        tax.setDescription(taxDTO.getDescription());
        tax.setRate(taxDTO.getRate());

        Tax savedTax = new Tax();
        savedTax.setId(1L);
        savedTax.setName(taxDTO.getName());
        savedTax.setDescription(taxDTO.getDescription());
        savedTax.setRate(taxDTO.getRate());

        when(taxRepository.save(any(Tax.class))).thenReturn(savedTax);

        // Act
        TaxDTO result = createTax.addTax(taxDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("VAT", result.getName());
        assertEquals("Value Added Tax", result.getDescription());
        assertEquals(15.0, result.getRate());

        verify(taxRepository, times(1)).save(any(Tax.class));
    }
}