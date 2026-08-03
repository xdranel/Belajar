package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.model.dto.CreatePersonRequest;
import gendhiramona.webmvc.model.dto.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception{
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Gendhi");
        request.setMiddleName("Ramona");
        request.setLastName("Prastyo");
        request.setEmail("abc@example.com");
        request.setPhone("0123456789");
        request.setHobbies(List.of("Reading", "Traveling", "Coding"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "https://www.facebook.com/gendhiramona"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "https://www.instagram.com/gendhiramona"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonValidationError() throws Exception{
        CreatePersonRequest request = new CreatePersonRequest();
        request.setMiddleName("Ramona");
        request.setLastName("Prastyo");
        request.setHobbies(List.of("Reading", "Traveling", "Coding"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "https://www.facebook.com/gendhiramona"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "https://www.instagram.com/gendhiramona"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation error: "))
        );
    }
}