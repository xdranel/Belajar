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
class TodoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addTodo() throws Exception {
        mockMvc.perform(
                post("/todos")
                        .param("todo", "Buy milk")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Buy milk"))
        );
    }

    @Test
    void getTodo() throws Exception {
        mockMvc.perform(
                get("/todos")
        ).andExpectAll(
                status().isOk()
        );
    }
}