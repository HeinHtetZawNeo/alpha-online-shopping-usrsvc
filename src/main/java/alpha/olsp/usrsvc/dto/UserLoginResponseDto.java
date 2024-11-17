package alpha.olsp.usrsvc.dto;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public class UserLoginResponseDto {
    private String userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
