package dev.qwerty7878.mysns.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.entity.Feed;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FeedService;
import dev.qwerty7878.mysns.service.UserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class FeedControllerTest {

    private FeedService feedService;
    private UserService userService;
    private FeedController feedController;

    @BeforeEach
    void setUp() {
        feedService = mock(FeedService.class);
        userService = mock(UserService.class);
        feedController = new FeedController(feedService, userService);
    }

    @Test
    void createFeedSuccess() {
        User user = new User();
        user.setId("user1");

        Feed feed = new Feed();
        feed.setUser(user);

        when(userService.getUserById("user1")).thenReturn(Optional.of(user));
        when(feedService.createFeed(any(Feed.class))).thenReturn(feed);

        ResponseEntity<Feed> response = feedController.createFeed(feed);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(feed, response.getBody());
    }

    @Test
    void getFeedByIdFound() {
        Feed feed = new Feed();
        when(feedService.getFeed(1L)).thenReturn(Optional.of(feed));

        ResponseEntity<Feed> response = feedController.getFeedByFeedId(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(feed, response.getBody());
    }

    @Test
    void getFeedByUserId() {
        List<Feed> feeds = List.of(new Feed(), new Feed());
        when(feedService.getFeedsByUserId("user1")).thenReturn(feeds);

        ResponseEntity<List<Feed>> response = feedController.gerFeedByUserId("user1");
        assertEquals(2, response.getBody().size());
    }
}