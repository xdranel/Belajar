package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.model.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @PostMapping(
            path = "/auth/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if ("admin".equals(username) && "admin".equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", new User(username));

            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            response.addCookie(cookie);

            //return ResponseEntity.ok("Login success");
            return ResponseEntity.status(HttpStatus.OK).body("Login Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout success");
    }

    @GetMapping("/auth/user")
    public ResponseEntity<String> getUser(
            @CookieValue(name = "username") String username
    ) {
        return ResponseEntity.ok("User: " + username);
    }

}
