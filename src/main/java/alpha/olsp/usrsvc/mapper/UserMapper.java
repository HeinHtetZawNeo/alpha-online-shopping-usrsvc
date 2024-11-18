package alpha.olsp.usrsvc.mapper;

import alpha.olsp.usrsvc.dto.*;
import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.model.Seller;

public class UserMapper {
    public static AdminRegisterResponesDto adminToAdminRegisterResponesDto(Admin admin) {
        return AdminRegisterResponesDto.builder()
                .userID(admin.getUserID())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .address(AddressDto.builder()
                        .addressLine1(admin.getAddress().getAddressLine1())
                        .addressLine2(admin.getAddress().getAddressLine2())
                        .city(admin.getAddress().getCity())
                        .state(StateDto.builder()
                                .stateId(admin.getAddress().getState().getStateID())
                                .stateName(admin.getAddress().getState().getStateName())
                                .build())
                        .postalCode(admin.getAddress().getPostalCode())
                        .build()
                )
                .isAccountNonExpired(admin.getIsAccountNonExpired())
                .isAccountNonLocked(admin.getIsAccountNonLocked())
                .isCredentialsNonExpired(admin.getIsCredentialsNonExpired())
                .isEnabled(admin.getIsEnabled())
                .build();
    }

    public static SellerRegisterResponesDto sellerToSellerRegisterResponesDto(Seller seller) {
        return SellerRegisterResponesDto.builder()
                .userID(seller.getUserID())
                .email(seller.getEmail())
                .firstName(seller.getFirstName())
                .lastName(seller.getLastName())
                .address(AddressDto.builder()
                        .addressLine1(seller.getAddress().getAddressLine1())
                        .addressLine2(seller.getAddress().getAddressLine2())
                        .city(seller.getAddress().getCity())
                        .state(StateDto.builder()
                                .stateId(seller.getAddress().getState().getStateID())
                                .stateName(seller.getAddress().getState().getStateName())
                                .build())
                        .postalCode(seller.getAddress().getPostalCode())
                        .build()
                )
                .isAccountNonExpired(seller.getIsAccountNonExpired())
                .isAccountNonLocked(seller.getIsAccountNonLocked())
                .isCredentialsNonExpired(seller.getIsCredentialsNonExpired())
                .isEnabled(seller.getIsEnabled())
                .build();
    }

    public static CustomerRegisterResponesDto customerToCustomerRegisterResponesDto(Customer customer) {
        return CustomerRegisterResponesDto.builder()
                .userID(customer.getUserID())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(AddressDto.builder()
                        .addressLine1(customer.getAddress().getAddressLine1())
                        .addressLine2(customer.getAddress().getAddressLine2())
                        .city(customer.getAddress().getCity())
                        .state(StateDto.builder()
                                .stateId(customer.getAddress().getState().getStateID())
                                .stateName(customer.getAddress().getState().getStateName())
                                .build())
                        .postalCode(customer.getAddress().getPostalCode())
                        .build()
                )
                .isAccountNonExpired(customer.getIsAccountNonExpired())
                .isAccountNonLocked(customer.getIsAccountNonLocked())
                .isCredentialsNonExpired(customer.getIsCredentialsNonExpired())
                .isEnabled(customer.getIsEnabled())
                .build();
    }
}
