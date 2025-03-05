package br.com.catalisa.zup.Tax.Calculator.Services;
import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxReposirtory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
public class CrateImposto {
    private final TaxReposirtory taxReposirtory;

    public CrateImposto(TaxReposirtory taxReposirtory) {
        this.taxReposirtory = taxReposirtory;
    }

    public TaxDTO addImpostos(TaxDTO taxDTO) {
        Tax addTax = new Tax();
        addTax.setId(taxDTO.getId());
        addTax.setName(taxDTO.getName());
        addTax.setDescription(taxDTO.getDescription());
        addTax.setRate(taxDTO.getRate());
        Tax add = taxReposirtory.save(addTax);
        return new TaxDTO(add.getId(), add.getName(), add.getDescription(), add.getRate());
    }
}