package dev.qwerty7878.mysns.controller;

import dev.qwerty7878.mysns.dto.LoginRequest;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.getUserById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 아이디가 존재합니다!");
        }
        user.setCreatedAt(LocalDateTime.now());
        userService.createUser(user);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return userService.getUserById(request.getId())
                .map(user -> request.getPassword().equals(user.getPassword()) ?
                        ResponseEntity.ok("로그인 성공") :
                        ResponseEntity.status(401).body("비밀번호 불일치"))
                .orElse(ResponseEntity.status(401).body("아이디 없음"));
    }
}
