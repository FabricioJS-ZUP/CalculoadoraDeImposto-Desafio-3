package br.com.catalisa.zup.Tax.Calculator.Services;

import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxReposirtory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ListAllImpostos {
    private TaxReposirtory taxReposirtory;
    public ListAllImpostos(TaxReposirtory taxReposirtory) {
        this.taxReposirtory = taxReposirtory;
    }
    // Listar produtos
    // Listar produtos
    public List<TaxDTO> listarimpostos() {
        return taxReposirtory.findAll().stream()
                .map(tax -> new TaxDTO(tax.getId(), tax.getName(),tax.getDescription(),tax.getRate()))
                .collect(Collectors.toList());
    }
}
