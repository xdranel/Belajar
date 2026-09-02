package gendhiramona.webmvc.controller;

import org.hamcrest.Matchers;
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
class HeaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void headerOk() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .header("X-TOKEN", "OK")
        ).andExpectAll(
                status().isOk(),
                content().string("header-ok")
        );
    }

    @Test
    void headerNo() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .header("X-TOKEN", "WRONG")
        ).andExpectAll(
                status().isOk(),
                content().string("header-no")
        );
    }
}