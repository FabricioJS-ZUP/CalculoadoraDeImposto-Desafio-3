package br.com.catalisa.zup.Tax.Calculator.Services.User;

import br.com.catalisa.zup.Tax.Calculator.DTOs.User.AuthRequest;
import br.com.catalisa.zup.Tax.Calculator.DTOs.User.RegisterRequest;
import br.com.catalisa.zup.Tax.Calculator.Exceptions.BadRequestException;
import br.com.catalisa.zup.Tax.Calculator.Models.Role;
import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;


    public User registerUser(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        return userRepository.save(user);
    }

    public String authenticate(AuthRequest request) {
        User user = (User) userDetailsService.loadUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password for User: " + user.getUsername() + "!");
        }
        return jwtService.generateToken(user.getUsername(), user.getRole().name());

    }
}