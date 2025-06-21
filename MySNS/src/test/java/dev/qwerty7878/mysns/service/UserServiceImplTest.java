package dev.qwerty7878.mysns.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.repository.UserRepository;
import java.util.Arrays;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user));
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));
        assertEquals(2, userService.getAllUser().size());
    }

    @Test
    void getUserById() {
        User user = new User();
        when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        assertTrue(userService.getUserById("userId").isPresent());
    }

    @Test
    void deleteUser() {
        User user = new User();
        when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        userService.deleteUser("userId");
        verify(userRepository).deleteById("userId");
    }

    @Test
    void deleteUserException() {
        when(userRepository.findById("userId")).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser("userId"));
    }

    @Test
    void updateUser() {
        User origin = new User();
        User update = new User();
        update.setName("new");
        update.setPassword("newpw");
        update.setProfileImage("new.jpg");

        when(userRepository.findById("userId")).thenReturn(Optional.of(origin));
        when(userRepository.save(origin)).thenReturn(origin);

        User result = userService.updateUser("userId", update);

        assertEquals("new", result.getName());
        assertEquals("newpw", result.getPassword());
        assertEquals("new.jpg", result.getProfileImage());
    }
}