package br.com.catalisa.zup.Tax.Calculator.Repository;

import br.com.catalisa.zup.Tax.Calculator.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}