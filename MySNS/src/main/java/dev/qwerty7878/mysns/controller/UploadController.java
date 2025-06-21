package dev.qwerty7878.mysns.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Upload", description = "이미지 업로드")
@RestController
@RequestMapping("/v1/uploads")
@RequiredArgsConstructor
public class UploadController {

    @PostMapping("/profile")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        return handleUpload(file, "uploads/profiles/");
    }

    @PostMapping("/feed")
    public ResponseEntity<String> uploadFeedImage(@RequestParam("file") MultipartFile file) {
        return handleUpload(file, "uploads/feeds/");
    }

    private ResponseEntity<String> handleUpload(MultipartFile file, String folder) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("파일이 비어 있습니다.");
            }

            String extension = Optional.ofNullable(file.getOriginalFilename())
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(f.lastIndexOf(".")))
                    .orElse(".png");

            String fileName = UUID.randomUUID() + extension.toLowerCase();
            String fullFolder = System.getProperty("user.dir") + "/" + folder;
            Path path = Paths.get(fullFolder + fileName);

            Files.createDirectories(path.getParent());
            file.transferTo(path.toFile());

            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("파일 저장 실패: " + e.getMessage());
        }
    }
}
