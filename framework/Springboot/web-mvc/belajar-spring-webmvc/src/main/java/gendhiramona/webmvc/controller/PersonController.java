package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.model.dto.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    @PostMapping(
            path = "/person",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createPerson(
            @Valid @RequestBody @ModelAttribute
            CreatePersonRequest request, BindingResult bindingResult
    ) {
        //Binding Result only for validation error/exception
        //so for other error/exception it will not get catch

        // getAllErrors() returns a list of all errors in the request
        // List<ObjectError> errors = bindingResult.getAllErrors();
        // if u want to get the field too
        List<FieldError> errors = bindingResult.getFieldErrors();

        if (!errors.isEmpty()) {
            // error
            // getAllErrors using objectError
            //errors.forEach(objectError -> {
            //    System.out.println(objectError.getDefaultMessage());
            //});

            // field error using fieldError
            errors.forEach(fieldError -> {
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Data");
        }

        String response = new StringBuilder().append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName()).append(" ")
                .append("with email ").append(request.getEmail()).append(" ")
                .append("and phone ").append(request.getPhone()).append(" ")
                .append("with address ")
                .append(request.getAddress().getStreet()).append(", ")
                .append(request.getAddress().getCity()).append(", ")
                .append(request.getAddress().getCountry()).append(", ")
                .append(request.getAddress().getPostalCode()).append(" ")
                .append("hobbies: ")
                .append(request.getHobbies()).append(" ")
                .append("social medias: ")
                .append(request.getSocialMedias())
                .toString();

        return ResponseEntity.ok(response);
    }

//    "Success create person " +
//                request.getFirstName() + " " +
//                request.getMiddleName() + " " +
//                request.getLastName() + " " +
//                "with email " + request.getEmail() + " " +
//                "and phone " + request.getPhone() + " " +
//                "with address " +
//                request.getAddress().getStreet() + ", " +
//                request.getAddress().getCity() + ", " +
//                request.getAddress().getCountry() + ", " +
//                request.getAddress().getPostalCode() + " " +
//                "hobbies: " +
//                request.getHobbies() + " " +
//                "social medias: " +
//                request.getSocialMedias();
}
