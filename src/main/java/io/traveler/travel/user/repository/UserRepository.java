package io.traveler.travel.user.repository;


import io.traveler.travel.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndPassword(String email, String password);

    Optional<User> findBynickname(String nickname);
}
