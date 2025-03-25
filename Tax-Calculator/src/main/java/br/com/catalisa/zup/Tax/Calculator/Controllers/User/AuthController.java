package br.com.catalisa.zup.Tax.Calculator.Controllers.User;

import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthResponse;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.UserResponse;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.AuthService;
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
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
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
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse(authService.authenticate(request));
    }
}
