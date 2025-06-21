package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.Feed;
import java.util.List;
import java.util.Optional;

public interface FeedService {
    Feed createFeed(Feed feed);

    List<Feed>  getFeedsByUserId(String userId);

    Optional<Feed> getFeed(Long feedId);

    Feed updateFeed(Long feedId, Feed updateFeed);

    void deleteFeed(Long feedId);
}
