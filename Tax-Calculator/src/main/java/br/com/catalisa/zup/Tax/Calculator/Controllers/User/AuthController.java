package br.com.catalisa.zup.Tax.Calculator.Controllers.User;

import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthResponse;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.UserResponse;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary ="Criar usuário", description = "Criar um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Usuário criado"),
    })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        User savedUser = authService.registerUser(request);

        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary ="Gerar token do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Token gerado"),
            @ApiResponse(responseCode = "400",description = "Senha inválida para este usuário"),
            @ApiResponse(responseCode = "500",description = "Usuário não encontrado")

    })
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse(authService.authenticate(request));
    }
}
