package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Registrando usuários")
public class RegisterRequest {
    @Schema(description = "Nome do usuários")
    private String username;
    @Schema(description = "Senha do usuários")
    private String password;
    @Schema(description ="Esta variável serve para gerenciar as permissões do usuários" )
    private Role role;
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
