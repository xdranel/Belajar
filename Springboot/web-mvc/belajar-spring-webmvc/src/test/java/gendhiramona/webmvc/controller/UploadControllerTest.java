package gendhiramona.webmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadFile() throws Exception {
        mockMvc.perform(
                multipart("/upload/profile")
                        //.file(new MockMultipartFile("profile", "profile.png", "image/png", "profile".getBytes()))
                        .file(new MockMultipartFile("profile",
                                "profile.png", "image/png",
                                getClass().getResourceAsStream("/images/profile.png")))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("name", "Gendhi")
        ).andExpectAll(
                status().isOk(),
                content().string("Success save profile Gendhi to upload/profile.png")
        );
    }
}