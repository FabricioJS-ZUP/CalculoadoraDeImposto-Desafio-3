package br.com.catalisa.zup.Tax.Calculator.Controllers.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.CalcTax;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.CreateTax;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.DeleteTax;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.ViewTax;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for TaxController")
@ActiveProfiles("test")
class TaxControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DeleteTax deleteTax;

    @Mock
    private ViewTax viewTax;

    @Mock
    private CreateTax createTax;

    @Mock
    private CalcTax calcTax;

    @InjectMocks
    private TaxController taxController;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taxController).build();
    }

    @Nested
    @DisplayName("Tests for the addTax method")
    class AddTaxTests {

        @Test
        @DisplayName("Add tax successfully")
        void shouldAddTaxSuccessfully() throws Exception {
            // Arrange
            TaxDTO taxDTO = new TaxDTO(null, "Tax Name", "Tax Description", 10.0);
            TaxDTO savedTaxDTO = new TaxDTO(1L, "Tax Name", "Tax Description", 10.0);
            Mockito.when(createTax.addTax(any(TaxDTO.class))).thenReturn(savedTaxDTO);

            // Act & Assert
            mockMvc.perform(post("/impostos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(taxDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.name").value("Tax Name"))
                    .andExpect(jsonPath("$.description").value("Tax Description"))
                    .andExpect(jsonPath("$.rate").value(10.0));

            Mockito.verify(createTax, Mockito.times(1)).addTax(any(TaxDTO.class));
        }
    }

    @Nested
    @DisplayName("Tests for the getAllTaxes method")
    class GetAllTaxesTests {

        @Test
        @DisplayName("Must return all taxes successfully")
        void shouldReturnAllTaxesSuccessfully() throws Exception {
            // Arrange
            List<TaxDTO> taxes = List.of(
                    new TaxDTO(1L, "Tax1", "Description1", 10.0),
                    new TaxDTO(2L, "Tax2", "Description2", 20.0)
            );
            Mockito.when(viewTax.getAllTaxes()).thenReturn(taxes);

            // Act & Assert
            mockMvc.perform(get("/impostos"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(2))
                    .andExpect(jsonPath("$[0].name").value("Tax1"))
                    .andExpect(jsonPath("$[1].name").value("Tax2"));

            Mockito.verify(viewTax, Mockito.times(1)).getAllTaxes();
        }

        @Test
        @DisplayName("Should return empty list when there are no taxes")
        void shouldReturnEmptyListWhenNoTaxesExist() throws Exception {
            // Arrange
            Mockito.when(viewTax.getAllTaxes()).thenReturn(Collections.emptyList());

            // Act & Assert
            mockMvc.perform(get("/impostos"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(0));

            Mockito.verify(viewTax, Mockito.times(1)).getAllTaxes();
        }
    }

    @Nested
    @DisplayName("Tests for the getTaxById method")
    class GetTaxByIdTests {

        @Test
        @DisplayName("Must return a tax by ID successfully")
        void shouldReturnTaxByIdSuccessfully() throws Exception {
            // Arrange
            Long taxId = 1L;
            TaxDTO taxDTO = new TaxDTO(taxId, "Tax1", "Description1", 10.0);
            Mockito.when(viewTax.getTaxById(taxId)).thenReturn(taxDTO);

            // Act & Assert
            mockMvc.perform(get("/impostos/{id}", taxId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(taxId))
                    .andExpect(jsonPath("$.name").value("Tax1"))
                    .andExpect(jsonPath("$.description").value("Description1"))
                    .andExpect(jsonPath("$.rate").value(10.0));

            Mockito.verify(viewTax, Mockito.times(1)).getTaxById(taxId);
        }
    }

    @Nested
    @DisplayName("Tests for the deleteTax method")
    class DeleteTaxTests {

        @Test
        @DisplayName("Must delete a tax successfully")
        void shouldDeleteTaxSuccessfully() throws Exception {
            // Arrange
            Long taxId = 1L;

            // Act & Assert
            mockMvc.perform(delete("/impostos/{id}", taxId))
                    .andExpect(status().isNoContent());

            Mockito.verify(deleteTax, Mockito.times(1)).deleteTax(taxId);
        }

    }
}