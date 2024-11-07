package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Customer;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> registerCustomer(Customer customer);
}
