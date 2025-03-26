package io.traveler.travel.trip.repository;

import io.traveler.travel.trip.entity.Trip;
import io.traveler.travel.user.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Slice<Trip> findAllByUser(User user, Pageable pageable);
}
