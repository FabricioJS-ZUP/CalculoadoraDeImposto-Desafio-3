package br.com.catalisa.zup.Tax.Calculator.DTOs;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

//@Getter
//@Setter
@Builder
public class TaxDTO {
    private Long id;

    @NotNull(message = "The name cannot be null.")
    @Size(min = 1, max = 20, message = "The name must be between 1 and 20 characters long.")
    private String name;

    @Size(min = 1, max = 100, message = "The description must be between 1 and 100 characters long.")
    @NotNull(message = "Description cannot be null.")
    private String description;

    @NotNull(message = "The rate cannot be zero.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The tax rate must be greater than 0.")
    @DecimalMax(value = "100.0", inclusive = true, message = "The rate cannot be greater than 100.")
    private Double rate;

    public TaxDTO(Long id, String name, String description, Double rate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public TaxDTO(String name, String description, Double rate) {
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public TaxDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}