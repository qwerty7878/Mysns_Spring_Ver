package dev.qwerty7878.mysns.controller.inte;

import dev.qwerty7878.mysns.controller.UploadController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UploadController.class)
public class UploadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadProfileImageEmptyFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", "image/png", new byte[0]);

        mockMvc.perform(multipart("/v1/uploads/profile").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("파일이 비어 있습니다."));
    }

    @Test
    void uploadFeedImageEmptyFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", "image/png", new byte[0]);

        mockMvc.perform(multipart("/v1/uploads/feed").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("파일이 비어 있습니다."));
    }
}
