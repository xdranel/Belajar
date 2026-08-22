package gendhiramona.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void helloGuest() throws Exception {
//        The entire HTTP response body must match exactly
//        Very rigid. Fails if you add a newline or a <p> tag later.
//        mockMvc.perform(
//                get("/hello")
//        ).andExpectAll(
//                status().isOk(),
//                content().contentType("text/html"),
//                content().string("<h1>Hello Guest</h1>")
//        );
//
//        The snippet must exist anywhere in the body.
//        Moderate. Fails if you change attributes inside the tag (e.g., <h1 class="title">).
//        If you just want to verify that the <h1>Hello Guest</h1> tag exists anywhere in the response body,
//        use Hamcrest's containsString. This is the most common approach for basic HTML validation
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("<h1>Hello Guest</h1>")));
//
//        The actual XML/HTML node structure and its inner text.
//        High. Only cares about the <h1> element and its text content.
//        To check the text inside the <h1> tag
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(xpath("//h1").string("Hello Guest"));
//
//        To just check that an <h1> tag exists
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(xpath("//h1").exists())
//    }

    @Test
    void helloGuest() throws Exception{
        //Test the Redirect
        mockMvc.perform(get("/hello"))
                .andExpectAll(
                        status().is(302),
//                        header().string("Location", "/hello?name=Guest"),
                        redirectedUrl("/hello?name=Guest")
                );
//                .andExpect(redirectedUrl("/hello?name=Guest"));

        //Pass the Parameter to Test the HTML Content
//        mockMvc.perform(get("/hello").queryParam("name", "Guest"))
//                .andExpectAll(
//                        status().isOk(),
//                        xpath("//h1").string("Hello Guest")
//                );
    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(
                get("/hello").queryParam("name", "Gendhi")
                ).andExpectAll(
                        status().isOk(),
                        xpath("//h1").string("Hello Gendhi")
                );
    }

    @Test
    void helloPost() throws Exception {
        mockMvc.perform(
                post("/hello").queryParam("name", "Gendhi")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );
    }

    @Test
    void helloView() throws Exception {
        mockMvc.perform(
                get("/web/hello").queryParam("name", "Gendhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Belajar View")),
                content().string(Matchers.containsString("Hello Gendhi"))
        );
    }
}