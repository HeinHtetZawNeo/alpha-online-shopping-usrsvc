package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/a")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin user) {
        Optional<Admin> registeredAdmin = adminService.registerAdmin(user);
        if(registeredAdmin.isPresent()) {
            return ResponseEntity.ok(registeredAdmin.get());
        }else {
            throw new InvalidInputException("Registration failed");
        }

    }
}
