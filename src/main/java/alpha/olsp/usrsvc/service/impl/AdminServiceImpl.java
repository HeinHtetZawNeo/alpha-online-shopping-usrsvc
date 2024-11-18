package alpha.olsp.usrsvc.service.impl;

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

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final AdminRepository adminRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Admin> registerAdmin(Admin admin) {
        logger.info("registerAdmin : " + admin);

        if (userService.findUserByEmail(admin.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return Optional.of(adminRepository.save(admin));
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
