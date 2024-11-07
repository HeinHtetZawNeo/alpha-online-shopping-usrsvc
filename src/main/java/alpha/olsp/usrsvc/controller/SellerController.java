package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/s")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller user) {
        Optional<Seller> registeredSeller = sellerService.registerSeller(user);
        if (registeredSeller.isPresent())
            return ResponseEntity.ok(registeredSeller.get());
        else
            throw new InvalidInputException("Registration failed");
    }
}
