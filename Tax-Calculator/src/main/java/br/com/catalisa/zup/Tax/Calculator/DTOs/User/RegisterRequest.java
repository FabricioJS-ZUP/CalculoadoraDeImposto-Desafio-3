package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
