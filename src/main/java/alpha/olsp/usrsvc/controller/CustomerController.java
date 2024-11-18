package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.CustomerRegisterResponesDto;
import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.mapper.UserMapper;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponesDto> registerCustomer(@RequestBody Customer customer) {

        Optional<Customer> registeredCustomer = customerService.registerCustomer(customer);
        if (registeredCustomer.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.customerToCustomerRegisterResponesDto(registeredCustomer.get()));
        else
            throw new InvalidInputException("Registration failed");
    }
}
