package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ViewTax {
    private final TaxRepository taxRepository;

    public List<TaxDTO> getAllTaxes() {
        return taxRepository.findAll().stream()
                .map(tax -> new TaxDTO(tax.getId(), tax.getName(), tax.getDescription(), tax.getRate()))
                .collect(Collectors.toList());
    }

    public TaxDTO getTaxById(Long id) {
        Tax tax = taxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tax not find"));
        return new TaxDTO(tax.getId(), tax.getName(), tax.getDescription(), tax.getRate());
    }
}
