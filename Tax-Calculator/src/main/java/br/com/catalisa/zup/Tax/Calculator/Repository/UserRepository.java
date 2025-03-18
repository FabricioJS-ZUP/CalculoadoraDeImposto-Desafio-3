package br.com.catalisa.zup.Tax.Calculator.Repository;

import br.com.catalisa.zup.Tax.Calculator.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}