package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(String userId) {
        if (userRepository.findById(userId) == null || userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(String userId, User updateUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setPassword(updateUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
    }
}
