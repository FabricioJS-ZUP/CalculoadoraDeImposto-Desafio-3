package br.com.catalisa.zup.Tax.Calculator.Services.Tax;

import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.ResourceNotFoundException;
import br.com.catalisa.zup.Tax.Calculator.Repository.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTax {
    private final TaxRepository taxRepository;
    public void deleteTax(Long id) {
        if (id == null) {
            throw new BadRequestException("Tax ID cannot be null");
        }
        if (!taxRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tax not found with ID: " + id);
        }
        taxRepository.deleteById(id);
    }
}

