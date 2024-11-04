package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.UserLoginRequest;
import alpha.olsp.usrsvc.exception.InvalidCredentialsException;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.repository.UserRepository;
import alpha.olsp.usrsvc.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        User user = userOptional.orElseThrow(() ->
                new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return Map.of("token", token);
    }
}