package alpha.olsp.usrsvc.dto;

public record UserLoginRequestDto(
        String email,
        String password) {
}