package gendhiramona.webmvc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@AutoConfigureMockMvc
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloGuest() {
//        String response = restTemplate.getForObject("http://localhost:" + port + "/hello", String.class);
        String response = restTemplate.getForObject("/hello", String.class);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("<h1>Hello Guest</h1>"));
        Assertions.assertEquals("<h1>Hello Guest</h1>", response.trim());
    }

    @Test
    void helloName() {
//        String response = restTemplate.getForObject("http://localhost:" + port + "/hello?name=Gendhi", String.class);
        String response = restTemplate.getForObject("/hello?name=Gendhi", String.class);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("<h1>Hello Gendhi</h1>"));
        Assertions.assertEquals("<h1>Hello Gendhi</h1>", response.trim());
    }

//    @Test
//    void getPartnerError() throws Exception {
//        mockMvc.perform(
//                get("/partner/current")
//        ).andExpectAll(
//                status().is5xxServerError()
//        );
//    }

}
