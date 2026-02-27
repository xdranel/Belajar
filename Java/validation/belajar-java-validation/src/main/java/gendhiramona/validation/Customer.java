package gendhiramona.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @Email(message = "{customer.email.email}")
    @NotBlank(message = "{customer.email.notblank}")
    private String email;

    @NotBlank(message = "{customer.name.notblank}")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
