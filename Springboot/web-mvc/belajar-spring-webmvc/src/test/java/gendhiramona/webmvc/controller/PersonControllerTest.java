package gendhiramona.webmvc.controller;

import org.hamcrest.Matchers;
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
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Gendhi")
                        .param("middleName", "Ramona")
                        .param("lastName", "Prastyo")
                        .param("email", "grp@example.com")
                        .param("phone", "0123456789")
                        .param("address.street", "Jln. Ahmad Yani No 1")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12345")
                        .param("hobbies", "Reading", "Traveling", "Coding")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "https://www.facebook.com/gendhiramona")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "https://www.instagram.com/gendhiramona")
        ).andExpectAll(
                status().isOk(),
                content().string("Success create person " +
                        "Gendhi Ramona Prastyo with email grp@example.com and phone 0123456789 " +
                        "with address Jln. Ahmad Yani No 1, Jakarta, Indonesia, 12345 " +
                        "hobbies: [Reading, Traveling, Coding] " +
                        "social medias: " +
                        "[CreateSocialMediaRequest(name=Facebook, location=https://www.facebook.com/gendhiramona), " +
                        "CreateSocialMediaRequest(name=Instagram, location=https://www.instagram.com/gendhiramona)]")
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Ramona")
                        .param("lastName", "Prastyo")
                        .param("email", "grp@example.com")
                        .param("phone", "0123456789")
                        .param("address.street", "Jln. Ahmad Yani No 1")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12345")
                        .param("hobbies", "Reading", "Traveling", "Coding")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "https://www.facebook.com/gendhiramona")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "https://www.instagram.com/gendhiramona")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Invalid Data"))
        );
    }
}