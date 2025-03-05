package br.com.catalisa.zup.Tax.Calculator.Services;

import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxReposirtory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FindImpostoById {
    public TaxReposirtory taxReposirtory;

    public FindImpostoById(TaxReposirtory taxReposirtory) {
        this.taxReposirtory = taxReposirtory;
    }

    public TaxDTO searchid(Long id) {
        Optional<Tax> taxOptional = taxReposirtory.findById(id);
        if (taxOptional.isEmpty()) {
            throw new IllegalArgumentException("Imposto com o ID " + id + " não encontrado.");
        }
        Tax tax = taxOptional.get();
        return new TaxDTO(tax.getId(),tax.getName(),tax.getDescription(),tax.getRate());
    }
}