package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUser();

    Optional<User> getUserById(String userId);

    void deleteUser(String userId);

    User updateUser(String userId, User updateUser);
}
