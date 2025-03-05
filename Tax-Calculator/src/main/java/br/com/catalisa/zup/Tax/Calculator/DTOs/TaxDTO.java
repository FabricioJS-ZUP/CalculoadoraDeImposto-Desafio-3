package br.com.catalisa.zup.Tax.Calculator.DTOs;
import jakarta.annotation.Nullable;
import lombok.*;

@Setter
public class TaxDTO {
    @Nullable
    long id;
    @Nullable
    String name;
    @Nullable
    String description;
    @Nullable
    double rate;
    @Nullable
    double baseValue;
    @Nullable
    double taxValue;

    public TaxDTO(long id, String name, String description, double rate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public TaxDTO(long id, String name, String description, double rate, double baseValue, double taxValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.baseValue = baseValue;
        this.taxValue = taxValue;
    }

    public TaxDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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