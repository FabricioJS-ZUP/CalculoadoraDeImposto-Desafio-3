package br.com.catalisa.zup.Tax.Calculator.DTOs.Tax;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "Criando e modificando os dados dos impostos")
public class TaxDTO {
    private Long id;
    @Schema(description = "Nome do imposto")
    private String name;
    @Schema(description = "Descrição do imposto")
    private String description;

    @Schema(description = "Taxa do imposto")
    private Double rate;

    public TaxDTO(String name, String description, Double rate) {
        this.name = name;
        this.description = description;
        this.rate = rate;
    }
}