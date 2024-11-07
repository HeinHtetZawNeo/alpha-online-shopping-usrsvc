package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.exception.EmailAlreadyExistsException;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.repository.CustomerRepository;
import alpha.olsp.usrsvc.service.CustomerService;
import alpha.olsp.usrsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Customer> registerCustomer(Customer customer) {
        if(userService.findUserByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        //encode the password
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
