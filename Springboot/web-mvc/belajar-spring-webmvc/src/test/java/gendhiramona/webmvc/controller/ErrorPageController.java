package gendhiramona.webmvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping(path = "/error")
    public ResponseEntity<String> error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String html = """
                <html>
                <body>
                <h1>Error $statusCode</h1>
                <p>$errorMessage</p>
                </body>
                </html>
                """.replace("$statusCode", statusCode.toString())
                .replace("$errorMessage", errorMessage);
        return ResponseEntity.status(statusCode).body(html);
    }
}
