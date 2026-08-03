package gendhiramona.webmvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Controller
public class DateController {
//    making your own custom converter class like this not recommended since there's a bug
//    SimpleDateFormat is not thread-safe
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

//    Because @Component and @Controller are Spring Singletons (there is only one instance of them shared by all users)
//    If two users hit your website at the exact same fraction of a second, sdf.parse() or sdf.format() will glitch out and return wrong dates or crash
//    @GetMapping("/date")
//    public void getDate(@RequestParam(name = "date", required = false) Date date,
//                        HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//
//        response.getWriter().println("<h1>Today is " + sdf.format(Objects.requireNonNullElseGet(date, Date::new)) + "</h1>");
//        if (Objects.isNull(date)) {
//            response.getWriter().println("<h1>Please enter a date on the parameter</h1>");
//        } else {
//            response.getWriter().println("<h1>Today is " + sdf.format(date) + "</h1>");
//        }
//    }

//    Instead of java.util.Date and SimpleDateFormat
//    use the modern java.time.LocalDate and DateTimeFormatter (which are completely thread-safe)
//    @GetMapping("/date")
//    public void getDate(@RequestParam(name = "date", required = false)
//                        @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
//                        HttpServletResponse response) throws IOException {
//        // No custom converter class even needed anymore!
//        response.setContentType("text/html");
//        if (Objects.isNull(date)) {
//            response.getWriter().println("<h1>Please enter a date on the parameter</h1>");
//        } else {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            String formattedDate = date.format(formatter);
//            response.getWriter().println("<h1>Today is " + formattedDate + "</h1>");
//        }
//    }

    //    The way of Modern Spring Boot
//    @GetMapping(path = "/date", produces = MediaType.TEXT_HTML_VALUE)
    @GetMapping("/date")
    @ResponseBody
    public String getDate(@RequestParam(name = "date", required = false)
                          @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        if (Objects.isNull(date)) {
            return "<h1>Please enter a date(dd-mm-yyy) on the parameter</h1>";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "<h1>Today is " + date.format(formatter) + "</h1>";
    }
}
