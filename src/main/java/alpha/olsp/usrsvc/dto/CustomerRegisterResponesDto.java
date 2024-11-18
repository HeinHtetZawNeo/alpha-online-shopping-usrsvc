package alpha.olsp.usrsvc.dto;

import lombok.Builder;

@Builder
public record CustomerRegisterResponesDto(
        String userID,
        String email,
        String firstName,
        String lastName,
        AddressDto address,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
}
