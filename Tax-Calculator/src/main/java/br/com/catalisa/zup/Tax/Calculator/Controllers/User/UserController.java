package br.com.catalisa.zup.Tax.Calculator.Controllers.User;

import br.com.catalisa.zup.Tax.Calculator.Models.User;
import br.com.catalisa.zup.Tax.Calculator.Services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, @AuthenticationPrincipal UserDetails loggedUser) {
        if (!loggedUser.getUsername().equals(username)) {
            return ResponseEntity.status(403).build();
        }

        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
