package io.traveler.travel.trip.repository;

import io.traveler.travel.trip.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
