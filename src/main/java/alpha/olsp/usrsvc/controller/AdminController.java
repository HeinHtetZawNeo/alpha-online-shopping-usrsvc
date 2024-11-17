package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/a")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        if(admin.getAddress() == null)
            throw new InvalidInputException("Address is empty");

        Optional<Admin> registeredAdmin = adminService.registerAdmin(admin);
        if(registeredAdmin.isPresent()) {
            return ResponseEntity.ok(registeredAdmin.get());
        }else {
            throw new InvalidInputException("Registration failed");
        }
    }
}
