package dev.qwerty7878.mysns.controller.inte;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.qwerty7878.mysns.controller.AuthController;
import dev.qwerty7878.mysns.dto.LoginRequest;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }
//    @MockBean 대체가능

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerSuccess() throws Exception {
        User user = new User();
        user.setId("user1");

        Mockito.when(userService.getUserById("user1")).thenReturn(Optional.empty());
        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("회원가입 성공"));
    }

    @Test
    void loginSuccess() throws Exception {
        User user = new User();
        user.setId("user1");
        user.setPassword("pw");

        LoginRequest request = new LoginRequest();
        request.setId("user1");
        request.setPassword("pw");

        Mockito.when(userService.getUserById("user1")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("로그인 성공"));
    }
}
