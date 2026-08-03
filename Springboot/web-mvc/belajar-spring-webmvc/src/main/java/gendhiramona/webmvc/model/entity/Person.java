package gendhiramona.webmvc.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;
}
