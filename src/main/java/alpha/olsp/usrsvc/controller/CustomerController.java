package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/c")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer user) {
        Optional<Customer> registeredCustomer = customerService.registerCustomer(user);
        if(registeredCustomer.isPresent())
            return ResponseEntity.ok(registeredCustomer.get());
        else
            throw new InvalidInputException("Registration failed");
    }
}
