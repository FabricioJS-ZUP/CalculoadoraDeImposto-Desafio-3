package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "Token de acesso do usuário")
public class AuthResponse {
    private String token;
}
