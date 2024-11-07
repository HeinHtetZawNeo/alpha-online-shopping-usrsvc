package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Customer;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> registerCustomer(Customer customer);
    public Optional<Customer> findCustomerByEmail(String email);
}
