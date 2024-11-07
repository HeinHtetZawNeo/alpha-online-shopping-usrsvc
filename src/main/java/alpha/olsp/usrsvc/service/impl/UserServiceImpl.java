package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.repository.AdminRepository;
import alpha.olsp.usrsvc.repository.CustomerRepository;
import alpha.olsp.usrsvc.repository.SellerRepository;
import alpha.olsp.usrsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        System.out.println("Inside findUserByEmail");
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            return Optional.of(customerOptional.get());
        }
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(email);
        if (sellerOptional.isPresent()) {
            return Optional.of(sellerOptional.get());
        }
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return Optional.of(adminOptional.get());
        }
        return Optional.empty();
    }

}
