package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.UserLoginRequest;
import alpha.olsp.usrsvc.exception.InvalidCredentialsException;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.security.JwtUtil;
import alpha.olsp.usrsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginRequest request) {
        Optional<User> user= userService.findUserByEmail(request.getEmail());

        user.orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return Map.of("token", jwtUtil.generateToken(user.get().getEmail(),user.get().getClass().getSimpleName()));
    }
}