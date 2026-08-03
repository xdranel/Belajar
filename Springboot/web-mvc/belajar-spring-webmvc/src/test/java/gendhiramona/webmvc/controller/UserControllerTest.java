package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(
                get("/user/current")
                        .sessionAttr("user", new User("admin"))
        ).andExpectAll(
                status().isOk(),
                content().string("Hello admin")
        );
    }

    @Test
    void getUserInvalid() throws Exception {
        mockMvc.perform(
                get("/user/current")
        ).andExpectAll(
                status().is3xxRedirection()
        );
    }
}