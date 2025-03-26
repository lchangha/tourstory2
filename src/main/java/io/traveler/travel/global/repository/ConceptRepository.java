package io.traveler.travel.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.traveler.travel.global.entity.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Long> {

}
