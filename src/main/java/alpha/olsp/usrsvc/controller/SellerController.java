package alpha.olsp.usrsvc.controller;

import alpha.olsp.usrsvc.dto.SellerRegisterResponesDto;
import alpha.olsp.usrsvc.exception.InvalidInputException;
import alpha.olsp.usrsvc.mapper.UserMapper;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/register")
    public ResponseEntity<SellerRegisterResponesDto> registerSeller(@RequestBody Seller seller) {
        Optional<Seller> registeredSeller = sellerService.registerSeller(seller);
        if (registeredSeller.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.sellerToSellerRegisterResponesDto(registeredSeller.get()));
        else
            throw new InvalidInputException("Registration failed");
    }
}
