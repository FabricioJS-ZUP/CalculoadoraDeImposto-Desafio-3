package br.com.catalisa.zup.Tax.Calculator.Controllers.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/impostos")
public class TaxController {
    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping
    public ResponseEntity<TaxDTO> addTax(@RequestBody TaxDTO taxDTO) {
        return ResponseEntity.status(201).body(taxService.addTax(taxDTO));
    }

    @GetMapping
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxDTO> getTaxById(@PathVariable Long id) {
        return ResponseEntity.ok(taxService.getTaxById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calculo")
    public ResponseEntity<CalcTaxDTO> calculateTax(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf(request.get("id").toString());
        double baseValue = Double.valueOf(request.get("baseValue").toString());
        try {
            CalcTaxDTO taxDTO = taxService.calculateTax(id, baseValue);
            return ResponseEntity.ok(taxDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}