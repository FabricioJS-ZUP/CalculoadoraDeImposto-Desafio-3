package br.com.catalisa.zup.Tax.Calculator.Controllers.User;

import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
@RequiredArgsConstructor
@Tag(name = "Ver todas as suas informações")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    @Operation(summary ="Encontrar todas as suas informações")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Estas são todas as suas informações"),
            @ApiResponse(responseCode = "403",description = "Pesquise pelo seu nome e não por outros nomes de usuários"),
            @ApiResponse(responseCode = "500",description = "Você não colocou um nome ou o usuário não existe")
    })
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetails loggedUser) {
        if (!loggedUser.getUsername().equals(username)) {
            return ResponseEntity.status(403).build();
        }

        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
