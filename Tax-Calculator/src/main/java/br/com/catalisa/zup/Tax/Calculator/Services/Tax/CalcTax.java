package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.ResourceNotFoundException;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalcTax {
    private final TaxRepository taxRepository;

    public CalcTaxDTO calculateTax(Long id, double baseValue) {
        Tax tax = taxRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Tax with ID " + id + " not found!"));
        double taxValue = (tax.getRate() / 100) * baseValue;
        return new CalcTaxDTO(tax.getName(),baseValue,tax.getRate(), taxValue);
    }
}