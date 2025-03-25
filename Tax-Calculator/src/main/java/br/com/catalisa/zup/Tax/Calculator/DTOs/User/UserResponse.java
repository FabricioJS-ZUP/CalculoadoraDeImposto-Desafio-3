package br.com.catalisa.zup.Tax.Calculator.DTOs.User;

import br.com.catalisa.zup.Tax.Calculator.Models.Role;

public class UserResponse {

    private Long id;
    private String username;
    private Role role;

    // Construtor
    public UserResponse(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}