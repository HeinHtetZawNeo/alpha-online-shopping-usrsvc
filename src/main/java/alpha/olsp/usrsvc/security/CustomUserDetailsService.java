package alpha.olsp.usrsvc.security;

import alpha.olsp.usrsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<alpha.olsp.usrsvc.model.User> userOptional = userService.findUserByEmail(email);

        return org.springframework.security.core.userdetails.User
                .withUsername(userOptional.get().getEmail())
                .password(userOptional.get().getPassword())
                .authorities(userOptional.get().getClass().getName()).build();
    }
}