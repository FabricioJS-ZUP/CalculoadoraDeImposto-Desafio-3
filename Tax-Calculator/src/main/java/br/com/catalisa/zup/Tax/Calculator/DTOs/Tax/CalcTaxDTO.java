package br.com.catalisa.zup.Tax.Calculator.DTOs;


public class CalcTaxDTO {
    private String name;
    private String description;
    private double rate;
    private double baseValue;
    private double taxValue;

    public CalcTaxDTO(String name, String description, double rate, double baseValue, double taxValue) {
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.baseValue = baseValue;
        this.taxValue = taxValue;
    }

    public CalcTaxDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
    }
}
