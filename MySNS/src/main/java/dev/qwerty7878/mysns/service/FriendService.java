package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.User;
import java.util.Set;

public interface FriendService {
    void addFriend(String userId, String friendId);

    void deleteFriend(String userId, String friendId);

    Set<User> getFriends(String userId);
}
