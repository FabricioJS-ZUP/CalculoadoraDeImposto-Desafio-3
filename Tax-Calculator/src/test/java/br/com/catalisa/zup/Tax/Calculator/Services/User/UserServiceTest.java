package br.com.catalisa.zup.Tax.Calculator.Services.User;

import static org.junit.jupiter.api.Assertions.*;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Unit Tests")
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
//
//    @Nested
//    @DisplayName("Tests for getUserByUsername method")
//    class GetUserByUsernameTests {
//
//        @Test
//        @DisplayName("Should return user when username exists")
//        void shouldReturnUserWhenUsernameExists() {
//            // Arrange
//            String username = "testUser";
//            User mockUser = User.builder()
//                    .id(1L)
//                    .username(username)
//                    .password("password123")
//                    .role(Role.USER)
//                    .build();
//            when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
//
//            // Act
//            User result = userService.getUserByUsername(username);
//
//            // Assert
//            assertNotNull(result);
//            assertEquals(username, result.getUsername());
//            assertEquals("password123", result.getPassword());
//            verify(userRepository, times(1)).findByUsername(username);
//        }
//
////        @Test
////        @DisplayName("Should throw UsernameNotFoundException when username does not exist")
////        void shouldThrowExceptionWhenUsernameDoesNotExist() {
////            // Arrange
////            String username = "nonExistentUser";
////            when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
////
////            // Act & Assert
////            UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userService.getUserByUsername(username));
////            assertEquals("User not find", exception.getMessage());
////            verify(userRepository, times(1)).findByUsername(username);
////        }
//    }
}