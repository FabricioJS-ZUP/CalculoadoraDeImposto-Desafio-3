package br.com.catalisa.zup.Tax.Calculator.Controllers.User;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthResponse;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.UserResponse;
import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
@DisplayName("AuthController Unit Tests")
@ActiveProfiles("test")
class AuthControllerTest {

    @Nested
    @DisplayName("Tests for /register endpoint")
    class RegisterTests {

        @Test
        @DisplayName("Should register a user successfully")
        void shouldRegisterUserSuccessfully() {
            // Arrange
            AuthService mockAuthService = Mockito.mock(AuthService.class);
            AuthController authController = new AuthController(mockAuthService);

            RegisterRequest request = new RegisterRequest("testUser", "password123", Role.USER);
            User savedUser = User.builder()
                    .id(1L)
                    .username("testUser")
                    .role(Role.USER)
                    .build();

            Mockito.when(mockAuthService.registerUser(any(RegisterRequest.class))).thenReturn(savedUser);

            // Act
            ResponseEntity<UserResponse> response = authController.register(request);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(savedUser.getId(), response.getBody().getId());
            assertEquals(savedUser.getUsername(), response.getBody().getUsername());
            assertEquals(savedUser.getRole(), response.getBody().getRole());
        }

        @Test
        @DisplayName("Should throw exception when request is invalid")
        void shouldThrowExceptionWhenRequestIsInvalid() {
            // Arrange
            AuthService mockAuthService = Mockito.mock(AuthService.class);
            AuthController authController = new AuthController(mockAuthService);

            RegisterRequest invalidRequest = new RegisterRequest("", "", null);

            // Act & Assert
            assertThrows(Exception.class, () -> authController.register(invalidRequest));
        }
    }

    @Nested
    @DisplayName("Tests for /login endpoint")
    class LoginTests {

        @Test
        @DisplayName("Should authenticate user successfully")
        void shouldAuthenticateUserSuccessfully() {
            // Arrange
            AuthService mockAuthService = Mockito.mock(AuthService.class);
            AuthController authController = new AuthController(mockAuthService);

            AuthRequest request = new AuthRequest("testUser", "password123");
            String token = "jwtToken";

            Mockito.when(mockAuthService.authenticate(any(AuthRequest.class))).thenReturn(token);

            // Act
            AuthResponse response = authController.login(request);

            // Assert
            assertNotNull(response);
            assertEquals(token, response.getToken());
        }

        @Test
        @DisplayName("Should throw exception when credentials are invalid")
        void shouldThrowExceptionWhenCredentialsAreInvalid() {
            // Arrange
            AuthService mockAuthService = Mockito.mock(AuthService.class);
            AuthController authController = new AuthController(mockAuthService);

            AuthRequest invalidRequest = new AuthRequest("", "");

            Mockito.when(mockAuthService.authenticate(any(AuthRequest.class)))
                    .thenThrow(new RuntimeException("Invalid credentials"));

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> authController.login(invalidRequest));
            assertEquals("Invalid credentials", exception.getMessage());
        }
    }
}