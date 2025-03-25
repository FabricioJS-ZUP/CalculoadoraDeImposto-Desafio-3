package br.com.catalisa.zup.Tax.Calculator.Services.User;

import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Unit Tests")
@ActiveProfiles("test")
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
    void registerUser() {
    }

    @Test
    void authenticate() {
    }

    @Nested
    @DisplayName("Tests for registerUser method")
    class registerUser {

        @Test
        @DisplayName("Should register a new user successfully")
        void registerUser() {
            // Arrange
            RegisterRequest request = new RegisterRequest("testUser", "password123", Role.USER);
            User user = User.builder()
                    .username("testUser")
                    .password("encodedPassword")
                    .role(Role.USER)
                    .build();

            Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
            Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

            // Act
            User result = authService.registerUser(request);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals("testUser", result.getUsername());
            Assertions.assertEquals("encodedPassword", result.getPassword());
            Assertions.assertEquals(Role.USER, result.getRole());
            Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        }
    }

    @Nested
    @DisplayName("Tests for authenticate method")
    class authenticate {

        @Test
        @DisplayName("Should authenticate user with valid credentials")
        void shouldAuthenticateWithValidCredentials() {
            // Arrange
            AuthRequest request = new AuthRequest("testUser", "password123");
            User user = User.builder()
                    .username("testUser")
                    .password("encodedPassword")
                    .role(Role.USER)
                    .build();

            Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(user);
            Mockito.when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
            Mockito.when(jwtService.generateToken(user.getUsername(), user.getRole().name())).thenReturn("jwtToken");

            // Act
            String token = authService.authenticate(request);

            // Assert
            Assertions.assertNotNull(token);
            Assertions.assertEquals("jwtToken", token);
            Mockito.verify(userDetailsService, Mockito.times(1)).loadUserByUsername(request.getUsername());
            Mockito.verify(passwordEncoder, Mockito.times(1)).matches(request.getPassword(), user.getPassword());
        }

        @Test
        @DisplayName("Should throw exception for invalid password")
        void shouldThrowExceptionForInvalidPassword() {
            // Arrange
            AuthRequest request = new AuthRequest("testUser", "wrongPassword");
            User user = User.builder()
                    .username("testUser")
                    .password("encodedPassword")
                    .role(Role.USER)
                    .build();

            Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(user);
            Mockito.when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

            // Act & Assert
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                authService.authenticate(request);
            });

            Assertions.assertEquals("Invalid password for User: testUser!", exception.getMessage());
            Mockito.verify(userDetailsService, Mockito.times(1)).loadUserByUsername(request.getUsername());
            Mockito.verify(passwordEncoder, Mockito.times(1)).matches(request.getPassword(), user.getPassword());
        }
    }
}