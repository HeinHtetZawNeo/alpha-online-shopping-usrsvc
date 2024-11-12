package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.exception.UserNotFoundException;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.security.JwtUtil;
import alpha.olsp.usrsvc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/s")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
        Optional<Seller> registeredSeller = sellerService.registerSeller(seller);
        if (registeredSeller.isPresent())
            return ResponseEntity.ok(registeredSeller.get());
        else
            throw new InvalidInputException("Registration failed");
    }

    @GetMapping("/me")
    public ResponseEntity<Seller> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract token from the header (remove 'Bearer ' prefix)
        String token = authorizationHeader.substring(7);

        // Extract email from the token
        String email = jwtUtil.extractUsername(token);

        Optional<Seller> seller = sellerService.findSellerByEmail(email);
        if(seller.isPresent()) {
            return ResponseEntity.ok(seller.get());
        }else {
            throw new UserNotFoundException("Invalid User");
        }
    }
}
