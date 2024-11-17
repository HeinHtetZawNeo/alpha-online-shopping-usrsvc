package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.controller.AdminController;
import alpha.olsp.usrsvc.exception.EmailAlreadyExistsException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.repository.AdminRepository;
import alpha.olsp.usrsvc.service.AdminService;
import alpha.olsp.usrsvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public Optional<Admin> registerAdmin(Admin admin) {
        logger.info("registerAdmin : "+admin);

        if(userService.findUserByEmail(admin.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        //encode the password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return Optional.of(adminRepository.save(admin));
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
