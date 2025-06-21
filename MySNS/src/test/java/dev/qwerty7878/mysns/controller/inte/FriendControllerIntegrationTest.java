package dev.qwerty7878.mysns.controller.inte;

import dev.qwerty7878.mysns.controller.FriendController;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FriendService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FriendController.class)
public class FriendControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendService friendService;

    @Test
    void addFriendSuccess() throws Exception {
        mockMvc.perform(post("/v1/friends/add")
                        .param("userId", "user1")
                        .param("friendId", "friend1"))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 추가 성공"));
    }

    @Test
    void deleteFriendSuccess() throws Exception {
        mockMvc.perform(post("/v1/friends/delete")
                        .param("userId", "user1")
                        .param("friendId", "friend1"))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 삭제 성공"));
    }

    @Test
    void getFriendsSuccess() throws Exception {
        Mockito.when(friendService.getFriends(eq("user1"))).thenReturn(Set.of(new User()));

        mockMvc.perform(get("/v1/friends/user1"))
                .andExpect(status().isOk());
    }
}
