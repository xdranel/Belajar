package gendhiramona.webmvc.controller;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginSuccess() throws Exception{
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "admin")
                        .param("password", "admin")
        ).andExpectAll(
                status().isOk(),
                content().string("Login Success"),
                cookie().value("username", "admin")
        );
    }

    @Test
    void loginFailed() throws Exception{
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "admin")
                        .param("password", "")
        ).andExpectAll(
                status().isUnauthorized(),
                content().string("Login Failed")
        );
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(
                get("/auth/user")
                        .cookie(new Cookie("username", "admin"))
        ).andExpectAll(
                status().isOk(),
                content().string("User: admin")
        );
    }
}