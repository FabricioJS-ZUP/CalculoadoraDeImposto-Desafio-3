package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
    private Role role;
}