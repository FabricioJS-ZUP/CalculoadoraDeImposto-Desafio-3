package br.com.catalisa.zup.Tax.Calculator.Services.User;

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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByUsername_UserExists() {
        // Arrange
        String username = "testUser";
        User user = User.builder()
                .username(username)
                .password("password123")
                .role(Role.USER)
                .build();

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserByUsername(username);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(username, result.getUsername());
    }

    @Test
    void testGetUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByUsername(username);
        });
        Assertions.assertEquals("User not find", exception.getMessage());
    }}