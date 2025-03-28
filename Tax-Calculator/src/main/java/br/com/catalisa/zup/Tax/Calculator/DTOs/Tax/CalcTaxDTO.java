package br.com.catalisa.zup.Tax.Calculator.DTOs.Tax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalcTaxDTO {
    private String name;
    private double baseValue;
    private double rate;
    private double taxValue;
}