package io.traveler.travel.trip.repository;

import io.traveler.travel.trip.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageQueryRepository {

}
