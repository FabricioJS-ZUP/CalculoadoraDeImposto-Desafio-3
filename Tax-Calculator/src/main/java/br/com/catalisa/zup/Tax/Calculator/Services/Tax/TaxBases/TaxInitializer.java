package br.com.catalisa.zup.Tax.Calculator.Services.Tax.TaxBases;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.CreateTax;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaxInitializer implements CommandLineRunner {
    private final CreateTax createTax;

    @Override
    public void run(String... args) throws Exception {
        // Criando os impostos base
        TaxDTO icms = new TaxDTO();
        icms.setName("ICMS");
        icms.setDescription("Tax on Transactions relating to the Circulation of Goods and on Transport Services");
        icms.setRate(18.00);
        createTax.addTax(icms);

        TaxDTO iss = new TaxDTO();
        iss.setName("ISS");
        iss.setDescription("Service Tax");
        iss.setRate(3.75);
        createTax.addTax(iss);

        TaxDTO ipi = new TaxDTO();
        ipi.setName("IPI");
        ipi.setDescription("Tax on Industrialized Products");
        ipi.setRate(21.75);
        createTax.addTax(ipi);
    }
}