package br.com.catalisa.zup.Tax.Calculator.Controllers.Tax;

import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.CalcTaxDTO;
import br.com.catalisa.zup.Tax.Calculator.DTOs.Tax.TaxDTO;
import br.com.catalisa.zup.Tax.Calculator.Services.Tax.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/impostos")
@Tag(name = "Impostos")
@AllArgsConstructor
public class TaxController {
    private final DeleteTax deleteTax;
    private final ViewTax viewTax;
    private final CreateTax createTax;
    private final CalcTax calcTax;

    @PostMapping
    @Operation(summary ="Criar imposto", description = "Criar um novo imposto")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Imposto criado"),
            @ApiResponse(responseCode = "403",description = "Você não tem a autoridade para acessar esta funcionalidade")
    })
    public ResponseEntity<TaxDTO> addTax(@RequestBody TaxDTO taxDTO) {
        return ResponseEntity.status(201).body(createTax.addTax(taxDTO));
    }

    @GetMapping
    @Operation(summary ="Ver todos os impostos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Vizualizado com sucesso"),
    })
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        return ResponseEntity.ok(viewTax.getAllTaxes());
    }

    @GetMapping("/{id}")
    @Operation(summary ="Ver imposto ´por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Vizualizado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Não conseguimos encontrar o ID selecionado")
    })
    public ResponseEntity<TaxDTO> getTaxById(@PathVariable Long id) {
        return ResponseEntity.ok(viewTax.getTaxById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary ="Deletar imposto por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Vizualizado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Você não inseriu o id"),
            @ApiResponse(responseCode = "403",description = "Você não tem a autoridade para acessar esta funcionalidade"),
            @ApiResponse(responseCode = "404",description = "Não encontramos um imposto com este id")
    })
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        deleteTax.deleteTax(id);
        return ResponseEntity.noContent().build();
    }

        @PostMapping("/calculo")
        @Operation(summary ="Calcular taxa")
        @ApiResponses({
                @ApiResponse(responseCode = "200",description = "Calculado com sucesso"),
                @ApiResponse(responseCode = "400",description = "Imposto escolhido não encontrado"),
                @ApiResponse(responseCode = "403",description = "Você não tem a autoridade para acessar esta funcionalidade")
        })
        public ResponseEntity<CalcTaxDTO> calculateTax(@RequestBody Map<String, Object> request) {
        Long id = Long.valueOf(request.get("id").toString());
        double baseValue = Double.valueOf(request.get("baseValue").toString());
        try {
            CalcTaxDTO taxDTO = calcTax.calculateTax(id, baseValue);
            return ResponseEntity.ok(taxDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}