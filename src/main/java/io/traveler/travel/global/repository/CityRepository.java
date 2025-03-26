package io.traveler.travel.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.traveler.travel.global.entity.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
