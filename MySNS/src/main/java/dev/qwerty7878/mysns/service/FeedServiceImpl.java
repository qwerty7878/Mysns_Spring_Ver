package dev.qwerty7878.mysns.service;

import dev.qwerty7878.mysns.entity.Feed;
import dev.qwerty7878.mysns.repository.FeedRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{

    private final FeedRepository feedRepository;

    @Override
    public Feed createFeed(Feed feed) {
        return feedRepository.save(feed);
    }

//    사용자가 작성한 모든 피드
    @Override
    public List<Feed> getFeedsByUserId(String userId) {
        return feedRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

//    하나의 피드
    @Override
    public Optional<Feed> getFeed(Long feedId) {
        return feedRepository.findById(feedId);
    }

    @Override
    public Feed updateFeed(Long feedId, Feed updateFeed) {
        return feedRepository.findById(feedId)
                .map(feed -> {
                    feed.setContent(updateFeed.getContent());
                    feed.setImages(updateFeed.getImages());
                    return feedRepository.save(feed);
                })
                .orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));
    }

    @Override
    public void deleteFeed(Long feedId) {
        if (feedRepository.findById(feedId) == null || feedRepository.findById(feedId).isEmpty()) {
            throw new IllegalArgumentException("피드가 존재하지 않습니다.");
        }
        feedRepository.deleteById(feedId);
    }
}
