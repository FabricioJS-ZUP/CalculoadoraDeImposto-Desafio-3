package br.com.catalisa.zup.Tax.Calculator.Services.Tax.TaxBases;import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.CreateTax;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.TaxBases.TaxInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class TaxInitializerTest {

    @Mock
    private CreateTax createTax;

    @InjectMocks
    private TaxInitializer taxInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun() throws Exception {
        // Act
        taxInitializer.run();

        // Assert
        verify(createTax, times(1)).addTax(argThat(tax ->
                tax.getName().equals("ICMS") &&
                        tax.getDescription().equals("Tax on Transactions relating to the Circulation of Goods and on Transport Services") &&
                        tax.getRate() == 18.00
        ));

        verify(createTax, times(1)).addTax(argThat(tax ->
                tax.getName().equals("ISS") &&
                        tax.getDescription().equals("Service Tax") &&
                        tax.getRate() == 3.75
        ));

        verify(createTax, times(1)).addTax(argThat(tax ->
                tax.getName().equals("IPI") &&
                        tax.getDescription().equals("Tax on Industrialized Products") &&
                        tax.getRate() == 21.75
        ));
    }
}