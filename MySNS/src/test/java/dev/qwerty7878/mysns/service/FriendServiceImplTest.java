package dev.qwerty7878.mysns.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.repository.UserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FriendServiceImplTest {

    private UserRepository userRepository;
    private FriendServiceImpl friendService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        friendService = new FriendServiceImpl(userRepository);
    }

    @Test
    void addFriend() {
        User user = new User();
        user.setFriends(new HashSet<>());
        User friend = new User();

        when(userRepository.findById("user")).thenReturn(Optional.of(user));
        when(userRepository.findById("friend")).thenReturn(Optional.of(friend));

        friendService.addFriend("user", "friend");

        assertTrue(user.getFriends().contains(friend));
        verify(userRepository).save(user);
    }

    @Test
    void deleteFriend() {
        User user = new User();
        User friend = new User();
        Set<User> friends = new HashSet<>();
        friends.add(friend);
        user.setFriends(friends);

        when(userRepository.findById("user")).thenReturn(Optional.of(user));
        when(userRepository.findById("friend")).thenReturn(Optional.of(friend));

        friendService.deleteFriend("user", "friend");

        assertFalse(user.getFriends().contains(friend));
        verify(userRepository).save(user);
    }

    @Test
    void getFriends() {
        User user = new User();
        Set<User> friends = new HashSet<>();
        friends.add(new User());
        user.setFriends(friends);

        when(userRepository.findById("user")).thenReturn(Optional.of(user));

        Set<User> result = friendService.getFriends("user");

        assertEquals(1, result.size());
    }
}