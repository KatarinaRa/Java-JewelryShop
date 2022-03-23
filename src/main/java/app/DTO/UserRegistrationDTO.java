package app.DTO;

import app.Validators.PasswordMatches;
import app.Validators.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserRegistrationDTO {
    private String name;
    private String surname;
    @ValidEmail
    private String email;
    private String password;
    private String confirmPassword;
}
