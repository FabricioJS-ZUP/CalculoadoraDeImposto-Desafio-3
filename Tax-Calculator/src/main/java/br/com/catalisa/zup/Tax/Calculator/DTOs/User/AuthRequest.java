package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Validando o usuário para poder obter o token")
public class AuthRequest {
    private String username;
    private String password;
}