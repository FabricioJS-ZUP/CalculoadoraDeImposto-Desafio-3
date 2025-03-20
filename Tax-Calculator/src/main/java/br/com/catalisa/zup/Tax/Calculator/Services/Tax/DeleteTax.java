package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTax {
    private final TaxRepository taxRepository;

    public void deleteTax(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tax ID cannot be null");
        }
        if (!taxRepository.existsById(id)) {
            throw new IllegalArgumentException("Tax not find");
        }
        taxRepository.deleteById(id);
    }
}

