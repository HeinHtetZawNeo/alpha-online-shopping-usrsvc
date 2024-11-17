package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.LoginResponseDto;
import alpha.olsp.usrsvc.dto.UserLoginRequestDto;
import alpha.olsp.usrsvc.exception.InvalidCredentialsException;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserLoginRequestDto request) {
        Optional<User> user= userService.findUserByEmailAndPassword(request.getEmail(),request.getPassword());
        user.orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));

        return ResponseEntity.ok(LoginResponseDto.builder()
                .userId(user.get().getUserID())
                .email(user.get().getEmail())
                .role(user.get().getClass().getSimpleName())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .createdAt(user.get().getCreatedAt())
                .updatedAt(user.get().getUpdatedAt())
                .build());
    }
}