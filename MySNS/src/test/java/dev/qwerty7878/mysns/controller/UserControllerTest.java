package dev.qwerty7878.mysns.controller;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void createUser() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUserById() {
        User user = new User();
        when(userService.getUserById("user1")).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.getUserById("user1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void getAllUser() {
        List<User> users = List.of(new User(), new User());
        when(userService.getAllUser()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getAllUser();
        assertEquals(2, response.getBody().size());
    }

    @Test
    void updateUser() {
        User user = new User();
        when(userService.updateUser(eq("user1"), any(User.class))).thenReturn(user);
        ResponseEntity<User> response = userController.updateUser("user1", user);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void deleteUser() {
        ResponseEntity<Void> response = userController.deleteUser("user1");
        assertEquals(204, response.getStatusCodeValue());
        verify(userService).deleteUser("user1");
    }
}
