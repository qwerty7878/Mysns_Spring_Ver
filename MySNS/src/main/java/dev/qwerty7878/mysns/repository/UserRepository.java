package dev.qwerty7878.mysns.repository;

import dev.qwerty7878.mysns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
