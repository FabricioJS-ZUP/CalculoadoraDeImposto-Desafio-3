package br.com.catalisa.zup.Tax.Calculator.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private List<String> roles; // Exemplo: "ROLE_ADMIN" ou "ROLE_USER"

    public Usuario(UUID id, String login, String senha, List<String> roles) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, List<String> roles) {
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario() {
    }
}