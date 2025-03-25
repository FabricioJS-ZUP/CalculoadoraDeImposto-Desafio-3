package br.com.catalisa.zup.Tax.Calculator.DTOs.Tax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxDTO {
    private Long id;
    private String name;
    private String description;
    private Double rate;
    public TaxDTO(String name, String description, Double rate) {
        this.name = name;
        this.description = description;
        this.rate = rate;
    }
}