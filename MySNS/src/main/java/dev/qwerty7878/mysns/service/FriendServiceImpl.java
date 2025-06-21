package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final UserRepository userRepository;
    @Override
    public void addFriend(String userId, String friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 없음"));

        user.getFriends().add(friend);
        userRepository.save(user);
    }

    @Override
    public void deleteFriend(String userId, String friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 없음"));

        user.getFriends().remove(friend);
        userRepository.save(user);
    }

    @Override
    public Set<User> getFriends(String userId) {
        return userRepository.findById(userId)
                .map(User::getFriends)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
    }
}
