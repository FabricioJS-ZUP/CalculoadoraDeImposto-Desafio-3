package br.com.catalisa.zup.Tax.Calculator.Repository;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("UserRepository Unit Tests")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save a user successfully")
    void shouldSaveUserSuccessfully() {
        // Arrange
        User user = User.builder()
                .username("testUser")
                .password("password123")
                .role(Role.USER)
                .build();

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser.getId());
        assertEquals("testUser", savedUser.getUsername());
        assertEquals("password123", savedUser.getPassword());
        assertEquals(Role.USER, savedUser.getRole());
    }

    @Nested
    @DisplayName("Tests for find a user by username")
    class findByUsername {
        @Test
        @DisplayName("Should find a user by username")
        void shouldFindUserByUsername() {
            // Arrange
            User user = User.builder()
                    .username("testUser")
                    .password("password123")
                    .role(Role.USER)
                    .build();
            userRepository.save(user);

            // Act
            Optional<User> foundUser = userRepository.findByUsername("testUser");

            // Assert
            assertTrue(foundUser.isPresent());
            assertEquals("testUser", foundUser.get().getUsername());
            assertEquals("password123", foundUser.get().getPassword());
            assertEquals(Role.USER, foundUser.get().getRole());
        }

        @Test
        @DisplayName("Should return empty when username does not exist")
        void shouldReturnEmptyWhenUsernameDoesNotExist() {
            // Act
            Optional<User> foundUser = userRepository.findByUsername("nonExistentUser");

            // Assert
            assertFalse(foundUser.isPresent());
        }

    }
}