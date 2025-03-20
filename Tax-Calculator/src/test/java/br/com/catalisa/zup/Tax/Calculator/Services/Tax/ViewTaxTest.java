package br.com.catalisa.zup.Tax.Calculator.Services.Tax;
import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.ViewTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ViewTaxTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private ViewTax viewTax;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTaxes() {
        // Arrange
        Tax tax1 = new Tax();
        tax1.setId(1L);
        tax1.setName("ICMS");
        tax1.setDescription("Tax on Transactions relating to the Circulation of Goods and on Transport Services");
        tax1.setRate(18.00);

        Tax tax2 = new Tax();
        tax2.setId(2L);
        tax2.setName("ISS");
        tax2.setDescription("Service Tax");
        tax2.setRate(3.75);

        when(taxRepository.findAll()).thenReturn(Arrays.asList(tax1, tax2));

        // Act
        List<TaxDTO> result = viewTax.getAllTaxes();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("ICMS", result.get(0).getName());
        assertEquals("Tax on Transactions relating to the Circulation of Goods and on Transport Services", result.get(0).getDescription());
        assertEquals(18.00, result.get(0).getRate());

        assertEquals(2L, result.get(1).getId());
        assertEquals("ISS", result.get(1).getName());
        assertEquals("Service Tax", result.get(1).getDescription());
        assertEquals(3.75, result.get(1).getRate());

        verify(taxRepository, times(1)).findAll();
    }

    @Test
    void testGetTaxById() {
        // Arrange
        Tax tax = new Tax();
        tax.setId(1L);
        tax.setName("ICMS");
        tax.setDescription("Tax on Transactions relating to the Circulation of Goods and on Transport Services");
        tax.setRate(18.00);

        when(taxRepository.findById(1L)).thenReturn(Optional.of(tax));

        // Act
        TaxDTO result = viewTax.getTaxById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("ICMS", result.getName());
        assertEquals("Tax on Transactions relating to the Circulation of Goods and on Transport Services", result.getDescription());
        assertEquals(18.00, result.getRate());

        verify(taxRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTaxByIdNotFound() {
        // Arrange
        when(taxRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> viewTax.getTaxById(1L));
        assertEquals("Tax not find", exception.getMessage());

        verify(taxRepository, times(1)).findById(1L);
    }
}