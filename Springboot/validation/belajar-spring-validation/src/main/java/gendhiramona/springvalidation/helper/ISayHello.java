package gendhiramona.springvalidation.helper;

import gendhiramona.springvalidation.data.Person;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ISayHello {

    String sayHello(@Valid Person person);
}
