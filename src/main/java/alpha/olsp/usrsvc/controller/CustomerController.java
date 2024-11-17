package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.model.Customer;
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

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        if(customer.getAddress() == null)
            throw new InvalidInputException("Address is empty");

        Optional<Customer> registeredCustomer = customerService.registerCustomer(customer);
        if(registeredCustomer.isPresent())
            return ResponseEntity.ok(registeredCustomer.get());
        else
            throw new InvalidInputException("Registration failed");
    }
}
