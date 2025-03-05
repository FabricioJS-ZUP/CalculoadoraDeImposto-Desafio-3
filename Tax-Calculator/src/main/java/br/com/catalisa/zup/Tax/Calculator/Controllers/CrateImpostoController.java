package br.com.catalisa.zup.Tax.Calculator.Controllers;

import br.com.catalisa.zup.Tax.Calculator.DTOs.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.CrateImposto;
import br.com.catalisa.zup.Tax.Calculator.Services.ImpostosBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/impostos/crate")
public class CrateImpostoController {
    private final CrateImposto crateImposto;

    public CrateImpostoController(CrateImposto crateImposto) {
        this.crateImposto = crateImposto;
    }

    // Cria clientes
    @PostMapping
    public ResponseEntity<TaxDTO> addImpostos(@RequestBody TaxDTO taxDTO) {
        return ResponseEntity.ok(crateImposto.addImpostos(taxDTO));
    }
}