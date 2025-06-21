package dev.qwerty7878.mysns.repository;

import dev.qwerty7878.mysns.entity.Feed;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByUserIdOrderByCreatedAtDesc(String userId);
}
