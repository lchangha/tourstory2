package io.traveler.travel.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.traveler.travel.trip.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
