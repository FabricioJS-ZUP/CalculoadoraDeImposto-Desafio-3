package br.com.catalisa.zup.Tax.Calculator.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "Tax")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Size(min = 1, max = 20, message = "The name must be between 1 and 20 characters long.")
    @NotNull(message = "The name cannot be null.")
    String name;

    @Column(nullable = false, length = 100)
    @Size(min = 1, max = 100, message = "The description must be between 1 and 100 characters long.")
    @NotNull(message = "Description cannot be null.")
    String description;

    @Column(nullable = false)
    @NotNull(message = "The rate cannot be zero.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The tax rate must be greater than 0.")
    @DecimalMax(value = "100.0", inclusive = true, message = "The rate cannot be greater than 100.")
    double rate;

    double baseValue;
    double taxValue;

    public Tax(long id, String name, String description, double rate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public Tax(String name, double baseValue, double rate, double taxValue) {
        this.name = name;
        this.baseValue = baseValue;
        this.rate = rate;
        this.taxValue = taxValue;
    }

    public Tax(String name, String description, double rate) {
        this.name = name;
        this.description = description;
        this.rate = rate;
    }
}

