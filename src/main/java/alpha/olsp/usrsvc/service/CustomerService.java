package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> registerCustomer(Customer customer);

    Optional<Customer> findCustomerByEmail(String email);
}
