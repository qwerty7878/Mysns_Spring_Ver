package dev.qwerty7878.mysns.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.qwerty7878.mysns.entity.Feed;
import dev.qwerty7878.mysns.repository.FeedRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedServiceImplTest {

    private FeedServiceImpl feedService;
    private FeedRepository feedRepository;

    @BeforeEach
    void setUp() {
        feedRepository = mock(FeedRepository.class);
        feedService = new FeedServiceImpl(feedRepository);
    }

    @Test
    void createFeed() {
        Feed feed = new Feed();
        when(feedRepository.save(feed)).thenReturn(feed);
        assertEquals(feed, feedService.createFeed(feed));
    }

    @Test
    void getFeedsByUserId() {
        List<Feed> list = Arrays.asList(new Feed(), new Feed());
        when(feedRepository.findByUserIdOrderByCreatedAtDesc("user")).thenReturn(list);
        assertEquals(2, feedService.getFeedsByUserId("user").size());
    }

    @Test
    void getFeed() {
        Feed feed = new Feed();
        when(feedRepository.findById(1L)).thenReturn(Optional.of(feed));
        assertTrue(feedService.getFeed(1L).isPresent());
    }

    @Test
    void updateFeed() {
        Feed origin = new Feed();
        Feed update = new Feed();
        update.setContent("new data");
        update.setImages(Arrays.asList("new.jpg"));

        when(feedRepository.findById(1L)).thenReturn(Optional.of(origin));
        when(feedRepository.save(origin)).thenReturn(origin);

        Feed result = feedService.updateFeed(1L, update);
        assertEquals("new data", result.getContent());
        assertEquals("new.jpg", result.getImages().get(0));
    }

    @Test
    void deleteFeed() {
        when(feedRepository.findById(1L)).thenReturn(Optional.of(new Feed()));
        feedService.deleteFeed(1L);
        verify(feedRepository).deleteById(1L);
    }

    @Test
    void deleteFeedException() {
        when(feedRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> feedService.deleteFeed(99L));
    }
}