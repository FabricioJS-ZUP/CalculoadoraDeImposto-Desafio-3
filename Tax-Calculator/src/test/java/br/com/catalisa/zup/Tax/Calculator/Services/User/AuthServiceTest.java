package br.com.catalisa.zup.Tax.Calculator.Services.User;

import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthService authService;

    @Test
    void testRegisterUser() {
        // Arrange
        RegisterRequest request = new RegisterRequest("testUser", "password123", Role.USER);
        User user = User.builder()
                .username(request.getUsername())
                .password("encodedPassword")
                .role(request.getRole())
                .build();

        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Act
        authService.registerUser(request);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void testAuthenticate_Success() {
        // Arrange
        AuthRequest request = new AuthRequest("testUser", "password123", Role.USER);
        User user = User.builder()
                .username(request.getUsername())
                .password("encodedPassword")
                .role(request.getRole())
                .build();

        Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(user);
        Mockito.when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        Mockito.when(jwtService.generateToken(user.getUsername(), user.getRole().name())).thenReturn("mockToken");

        // Act
        String token = authService.authenticate(request);

        // Assert
        Assertions.assertEquals("mockToken", token);
        Mockito.verify(jwtService, Mockito.times(1)).generateToken(user.getUsername(), user.getRole().name());
    }

    @Test
    void testAuthenticate_InvalidPassword() {
        // Arrange
        AuthRequest request = new AuthRequest("testUser", "wrongPassword", Role.USER);
        User user = User.builder()
                .username(request.getUsername())
                .password("encodedPassword")
                .role(request.getRole())
                .build();

        Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(user);
        Mockito.when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            authService.authenticate(request);
        });
        Assertions.assertEquals("Credenciais inválidas", exception.getMessage());
    }
}