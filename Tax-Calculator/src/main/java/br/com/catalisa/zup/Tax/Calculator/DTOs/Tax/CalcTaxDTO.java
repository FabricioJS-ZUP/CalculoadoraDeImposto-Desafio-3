package br.com.catalisa.zup.Tax.Calculator.DTOs.Tax;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "Calculando imposto")

public class CalcTaxDTO {
    @Schema(name = "name", description = "Nome do imposto")
    private String name;
    @Schema(name = "baseValue",description = "Valor base")
    private double baseValue;
    @Schema(name = "rate", description = "Taxa do imposto")
    private double rate;
    @Schema(name = "taxValue", description = "Valor a se pagar de imposto")
    private double taxValue;
}