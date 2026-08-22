package gendhiramona.webmvc.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Valid
    private CreateAddressRequest address;

    private List<String> hobbies;

    @NotEmpty(message = "Social media is required")
    private List<CreateSocialMediaRequest> socialMedias;
}
