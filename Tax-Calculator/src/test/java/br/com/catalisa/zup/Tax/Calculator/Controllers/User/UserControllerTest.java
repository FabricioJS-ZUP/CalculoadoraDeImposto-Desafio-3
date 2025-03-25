package br.com.catalisa.zup.Tax.Calculator.Controllers.User;

import static org.junit.jupiter.api.Assertions.*;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("UserController Unit Tests")
@ActiveProfiles("test")
class UserControllerTest {

    @Nested
    @DisplayName("Tests for /find/{username} endpoint")
    class GetUserByUsernameTests {

        @Test
        @DisplayName("Should return user details when username matches logged user")
        void shouldReturnUserDetailsWhenUsernameMatchesLoggedUser() {
            // Arrange
            UserService mockUserService = Mockito.mock(UserService.class);
            UserController userController = new UserController(mockUserService);

            String username = "testUser";
            UserDetails loggedUser = Mockito.mock(UserDetails.class);
            Mockito.when(loggedUser.getUsername()).thenReturn(username);

            User user = new User(1L, "username","password", Role.USER );
            Mockito.when(mockUserService.getUserByUsername(anyString())).thenReturn(user);

            // Act
            ResponseEntity<User> response = userController.getUserByUsername(username, loggedUser);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(user.getId(), response.getBody().getId());
            assertEquals(user.getUsername(), response.getBody().getUsername());
        }

        @Test
        @DisplayName("Should return 403 Forbidden when logged user does not match username")
        void shouldReturnForbiddenWhenLoggedUserDoesNotMatchUsername() {
            // Arrange
            UserService mockUserService = Mockito.mock(UserService.class);
            UserController userController = new UserController(mockUserService);

            String username = "testUser";
            UserDetails loggedUser = Mockito.mock(UserDetails.class);
            Mockito.when(loggedUser.getUsername()).thenReturn("differentUser");

            // Act
            ResponseEntity<User> response = userController.getUserByUsername(username, loggedUser);

            // Assert
            assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
            assertNull(response.getBody());
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void shouldThrowExceptionWhenUserIsNotFound() {
            // Arrange
            UserService mockUserService = Mockito.mock(UserService.class);
            UserController userController = new UserController(mockUserService);

            String username = "testUser";
            UserDetails loggedUser = Mockito.mock(UserDetails.class);
            Mockito.when(loggedUser.getUsername()).thenReturn(username);


            Mockito.when(mockUserService.getUserByUsername(anyString()))
                    .thenThrow(new UsernameNotFoundException("User not found"));

            // Act & Assert
            UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                    () -> userController.getUserByUsername(username, loggedUser));
            assertEquals("User not found", exception.getMessage());
        }
    }
}