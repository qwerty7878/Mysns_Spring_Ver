package dev.qwerty7878.mysns.controller.inte;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.qwerty7878.mysns.controller.UserController;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserSuccess() throws Exception {
        User user = new User();
        user.setId("user1");

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByIdSuccess() throws Exception {
        User user = new User();
        user.setId("user1");

        Mockito.when(userService.getUserById("user1")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/v1/users/user1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersSuccess() throws Exception {
        Mockito.when(userService.getAllUser()).thenReturn(List.of(new User(), new User()));

        mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserSuccess() throws Exception {
        User user = new User();
        user.setId("user1");

        Mockito.when(userService.updateUser(eq("user1"), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/v1/users/user1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserSuccess() throws Exception {
        mockMvc.perform(delete("/v1/users/user1"))
                .andExpect(status().isNoContent());
    }
}
