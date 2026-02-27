package gendhiramona.validation;

import gendhiramona.validation.constraint.CheckPassword;
import jakarta.validation.constraints.NotBlank;

@CheckPassword(message = "password and confirm password must match")
public class Register {

    @NotBlank(message = "{register.username.notblank}")
    private String username;

    @NotBlank(message = "{register.password.notblank}")
    private String password;

    @NotBlank(message = "{register.confirmPassword.notblank}")
    private String confirmPassword;

    @Override
    public String toString() {
        return "Register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
