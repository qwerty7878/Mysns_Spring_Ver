package dev.qwerty7878.mysns.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.dto.LoginRequest;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class AuthControllerTest {

    private UserService userService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        authController = new AuthController(userService);
    }

    @Test
    void register() {
        User user = new User();
        user.setId("user1");

        when(userService.getUserById("user1")).thenReturn(Optional.empty());
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<String> response = authController.register(user);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("회원가입 성공", response.getBody());
    }

    @Test
    void login() {
        User user = new User();
        user.setId("user1");
        user.setPassword("pw");

        when(userService.getUserById("user1")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest();
        request.setId("user1");
        request.setPassword("pw");

        ResponseEntity<String> response = authController.login(request);
        assertEquals("로그인 성공", response.getBody());
    }
}