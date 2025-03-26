package io.traveler.travel.user.repository;


import io.traveler.travel.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndPassword(String email, String password);

    Optional<User> findBynickname(String nickname);
}
