package gendhiramona.validation.service;

import gendhiramona.validation.constraint.CheckPasswordParameter;
import jakarta.validation.constraints.NotBlank;

public class UserService {

    @CheckPasswordParameter(
            passwordParam = 1,
            confirmPasswordParam = 2
    )
    public void register(@NotBlank(message = "username cannot blank") String username,
                         @NotBlank(message = "password cannot blank") String password,
                         @NotBlank(message = "confirm password cannot blank") String confirmPassword) {
        // TODO implement register method
    }
}
