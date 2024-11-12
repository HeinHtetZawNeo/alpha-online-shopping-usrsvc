package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.exception.UserNotFoundException;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.security.JwtUtil;
import alpha.olsp.usrsvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/c")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer user) {
        Optional<Customer> registeredCustomer = customerService.registerCustomer(user);
        if(registeredCustomer.isPresent())
            return ResponseEntity.ok(registeredCustomer.get());
        else
            throw new InvalidInputException("Registration failed");
    }
    @GetMapping("/me")
    public ResponseEntity<Customer> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract token from the header (remove 'Bearer ' prefix)
        String token = authorizationHeader.substring(7);

        // Extract email from the token
        String email = jwtUtil.extractUsername(token);

        Optional<Customer> customer = customerService.findCustomerByEmail(email);
        if(customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }else {
            throw new UserNotFoundException("Invalid User");
        }
    }
}
