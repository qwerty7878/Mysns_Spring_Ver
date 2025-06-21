package dev.qwerty7878.mysns.controller.inte;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.qwerty7878.mysns.controller.FeedController;
import dev.qwerty7878.mysns.entity.Feed;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FeedService;
import dev.qwerty7878.mysns.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeedController.class)
public class FeedControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedService feedService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFeedSuccess() throws Exception {
        User user = new User();
        user.setId("user1");

        Feed feed = new Feed();
        feed.setUser(user);

        Mockito.when(userService.getUserById("user1")).thenReturn(Optional.of(user));
        Mockito.when(feedService.createFeed(any(Feed.class))).thenReturn(feed);

        mockMvc.perform(post("/v1/feeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feed)))
                .andExpect(status().isOk());
    }
}
