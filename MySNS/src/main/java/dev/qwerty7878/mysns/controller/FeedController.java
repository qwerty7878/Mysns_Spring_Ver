package dev.qwerty7878.mysns.controller;

import dev.qwerty7878.mysns.entity.Feed;
import dev.qwerty7878.mysns.entity.User;
import dev.qwerty7878.mysns.service.FeedService;
import dev.qwerty7878.mysns.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed", description = "글쓰기 CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/feeds")
public class FeedController {
    private final FeedService feedService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Feed> createFeed(@RequestBody Feed feed) {
        String userId = feed.getUser() != null ? feed.getUser().getId() : null;
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.badRequest().body(null);
        }

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        feed.setUser(user);
        feed.setCreatedAt(LocalDateTime.now());

        Feed savedFeed = feedService.createFeed(feed);
        return ResponseEntity.ok(savedFeed);
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<Feed> getFeedByFeedId(@PathVariable Long feedId) {
        return feedService.getFeed(feedId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feed>> gerFeedByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(feedService.getFeedsByUserId(userId));
    }

    @PutMapping("/{feedId}")
    public ResponseEntity<Feed> updateFeed(@PathVariable Long feedId, @RequestBody Feed feed) {
        return ResponseEntity.ok(feedService.updateFeed(feedId, feed));
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long feedId) {
        feedService.deleteFeed(feedId);
        return ResponseEntity.noContent().build();
    }
}
