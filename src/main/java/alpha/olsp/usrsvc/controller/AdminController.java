package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.exception.UserNotFoundException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.security.JwtUtil;
import alpha.olsp.usrsvc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/a")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin user) {
        Optional<Admin> registeredAdmin = adminService.registerAdmin(user);
        if(registeredAdmin.isPresent()) {
            return ResponseEntity.ok(registeredAdmin.get());
        }else {
            throw new InvalidInputException("Registration failed");
        }
    }
    @GetMapping("/me")
    public ResponseEntity<Admin> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract token from the header (remove 'Bearer ' prefix)
        String token = authorizationHeader.substring(7);

        // Extract email from the token
        String email = jwtUtil.extractUsername(token);

        Optional<Admin> admin = adminService.findAdminByEmail(email);
        if(admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        }else {
            throw new UserNotFoundException("Invalid User");
        }
    }
}
