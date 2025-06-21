package dev.qwerty7878.mysns.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FriendService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class FriendControllerTest {

    private FriendService friendService;
    private FriendController friendController;

    @BeforeEach
    void setUp() {
        friendService = mock(FriendService.class);
        friendController = new FriendController(friendService);
    }

    @Test
    void addFriend() {
        ResponseEntity<String> response = friendController.addFriend("user1", "friend1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("친구 추가 성공", response.getBody());
        verify(friendService).addFriend("user1", "friend1");
    }

    @Test
    void deleteFriend() {
        ResponseEntity<String> response = friendController.deleteFriend("user1", "friend1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("친구 삭제 성공", response.getBody());
        verify(friendService).deleteFriend("user1", "friend1");
    }

    @Test
    void getFriends() {
        Set<User> friends = new HashSet<>(List.of(new User()));
        when(friendService.getFriends("user1")).thenReturn(friends);

        ResponseEntity<Set<User>> response = friendController.getFriends("user1");
        assertEquals(1, response.getBody().size());
    }
}