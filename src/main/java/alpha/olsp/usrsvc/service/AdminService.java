package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Admin;
import java.util.Optional;

public interface AdminService {
    Optional<Admin> registerAdmin(Admin user);
    Optional<Admin> findAdminByEmail(String email);
}
