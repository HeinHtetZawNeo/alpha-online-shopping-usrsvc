package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.UserLoginRequestDto;
import alpha.olsp.usrsvc.dto.UserLoginResponseDto;
import alpha.olsp.usrsvc.exception.InvalidCredentialsException;
import alpha.olsp.usrsvc.mapper.UserMapper;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        logger.trace("/api/v1/users/login with " + userLoginRequestDto);
        Optional<User> user = userService.findUserByEmailAndPassword(userLoginRequestDto.email(), userLoginRequestDto.password());

        user.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        return ResponseEntity.ok(UserMapper.userToUserLoginResponseDto(user.get()));
    }
}