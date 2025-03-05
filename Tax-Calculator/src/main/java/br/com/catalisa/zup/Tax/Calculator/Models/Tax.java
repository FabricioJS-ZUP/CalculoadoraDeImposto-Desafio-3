package br.com.catalisa.zup.Tax.Calculator.Models;

import jakarta.persistence.*;

@Entity
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @Column(unique = true)
    String description;
    double rate;
    double baseValue;
    double taxValue;

    public Tax(long id, String name, String description, double rate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public Tax(long id, String name, String description, double rate, double baseValue, double taxValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.baseValue = baseValue;
        this.taxValue = taxValue;
    }

    public Tax() {
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
