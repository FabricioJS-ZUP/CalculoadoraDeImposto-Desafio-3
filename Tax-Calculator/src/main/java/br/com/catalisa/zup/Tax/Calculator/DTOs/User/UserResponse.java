package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Validando o usuário para poder obter o token")
public class UserResponse {
    private Long id;
    private String username;
    private Role role;

    public UserResponse(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}