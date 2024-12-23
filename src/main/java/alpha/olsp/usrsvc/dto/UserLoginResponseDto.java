package alpha.olsp.usrsvc.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserLoginResponseDto(
        String userId,
        String email,
        String firstName,
        String lastName,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
}
