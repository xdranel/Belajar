package gendhiramona.springvalidation.helper;

import gendhiramona.springvalidation.data.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class SayHello implements ISayHello{

    public String sayHello(@Valid Person person) {
        return "Hello " + person.getName();
    }

}
