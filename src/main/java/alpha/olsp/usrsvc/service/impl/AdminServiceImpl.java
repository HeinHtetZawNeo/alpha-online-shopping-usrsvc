package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.exception.EmailAlreadyExistsException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.repository.AdminRepository;
import alpha.olsp.usrsvc.service.AdminService;
import alpha.olsp.usrsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Override
    public Optional<Admin> registerAdmin(Admin admin) {
        if(userService.findUserByEmail(admin.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        //encode the password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return Optional.of(adminRepository.save(admin));
    }
}
