package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalcTax {
    private final TaxRepository taxRepository;

    // Calcular imposto
    public CalcTaxDTO calculateTax(Long id, double baseValue) {
        Tax tax = taxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tax with ID " + id + " not found."));
        double taxValue = (tax.getRate() / 100) * baseValue;
        return new CalcTaxDTO(tax.getName(), tax.getDescription(), tax.getRate(), baseValue, taxValue);
    }
}