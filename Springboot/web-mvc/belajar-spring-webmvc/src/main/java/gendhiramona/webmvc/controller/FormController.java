package gendhiramona.webmvc.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
public class FormController {

    @PostMapping(path = "/form/hello",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name) {
        return """
                <html>
                <body>
                <h1>Hello $name</h1>
                </body>
                </html>
                """.replace("$name", name);
    }

    @PostMapping(path = "/form/person",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createPerson(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "dateOfBirth") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateOfBirth,
        @RequestParam(name = "address") String address
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Person created Name: " + name +
                ", Date of Birth: " + dateOfBirth.format(formatter) +
                ", Address: " + address;
    }


}
