package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> registerUser(User user);
    Optional<User> loginUser(String username,String password);
}
