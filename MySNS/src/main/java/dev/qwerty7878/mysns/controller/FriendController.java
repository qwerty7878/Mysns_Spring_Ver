package dev.qwerty7878.mysns.controller;

import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FriendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Friend", description = "친구 추가 및 삭제, 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/friends")
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam String userId, @RequestParam String friendId) {
        friendService.addFriend(userId, friendId);
        return ResponseEntity.ok("친구 추가 성공");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFriend(@RequestParam String userId, @RequestParam String friendId) {
        friendService.deleteFriend(userId, friendId);
        return ResponseEntity.ok("친구 삭제 성공");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Set<User>> getFriends(@PathVariable String userId) {
        return ResponseEntity.ok(friendService.getFriends(userId));
    }
}
