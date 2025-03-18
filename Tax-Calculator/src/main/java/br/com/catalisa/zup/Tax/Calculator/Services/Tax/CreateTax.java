package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTax {
    private final TaxRepository taxRepository;

    public TaxDTO addTax(TaxDTO taxDTO) {
        Tax tax = new Tax();
        tax.setName(taxDTO.getName());
        tax.setDescription(taxDTO.getDescription());
        tax.setRate(taxDTO.getRate());
        Tax savedTax = taxRepository.save(tax);
        return new TaxDTO(savedTax.getId(), savedTax.getName(), savedTax.getDescription(), savedTax.getRate());
    }
}
