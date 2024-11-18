package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.AdminRegisterResponesDto;
import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.mapper.UserMapper;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.service.AdminService;
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
@RequestMapping("/api/v1/users/admin")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AdminRegisterResponesDto> registerAdmin(@RequestBody Admin admin) {
        Optional<Admin> registeredAdmin = adminService.registerAdmin(admin);
        if (registeredAdmin.isPresent()) {
            return ResponseEntity.ok(UserMapper.adminToAdminRegisterResponesDto(registeredAdmin.get()));
        } else {
            throw new InvalidInputException("Registration failed");
        }
    }
}