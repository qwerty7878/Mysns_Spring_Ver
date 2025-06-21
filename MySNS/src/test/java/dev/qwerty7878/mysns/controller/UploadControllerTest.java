package dev.qwerty7878.mysns.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

class UploadControllerTest {

    private UploadController uploadController;

    @BeforeEach
    void setUp() {
        uploadController = new UploadController();
    }

    @Test
    void uploadProfileImage() {
        MockMultipartFile file = new MockMultipartFile("file", "", "image/png", new byte[0]);
        ResponseEntity<String> response = uploadController.uploadProfileImage(file);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("파일이 비어 있습니다.", response.getBody());
    }

    @Test
    void uploadFeedImage() {
        MockMultipartFile file = new MockMultipartFile("file", "", "image/png", new byte[0]);
        ResponseEntity<String> response = uploadController.uploadFeedImage(file);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("파일이 비어 있습니다.", response.getBody());
    }
}