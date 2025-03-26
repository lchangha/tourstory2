package io.traveler.travel.trip.service.impl;

import io.traveler.travel.global.entity.City;
import io.traveler.travel.global.entity.Concept;
import io.traveler.travel.global.repository.CityRepository;
import io.traveler.travel.global.repository.ConceptRepository;
import io.traveler.travel.trip.dto.input.*;
import io.traveler.travel.trip.dto.response.*;
import io.traveler.travel.trip.entity.*;
import io.traveler.travel.trip.repository.*;
import io.traveler.travel.trip.service.*;
import io.traveler.travel.user.entity.*;
import io.traveler.travel.user.repository.*;


import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final ConceptRepository conceptRepository;

    public TripServiceImpl(TripRepository tripRepository,
            UserRepository userRepository,
            CityRepository cityRepository,
            ConceptRepository conceptRepository) {

        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.conceptRepository = conceptRepository;
    }

    @Override
    public TripResponse getTrip(long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        return TripResponse.from(trip);
    }

    @Override
    public TripResponse getOwnTrip(String email, long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        if (trip.getUser().getEmail().equals(email)) {
            return TripResponse.from(trip);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Slice<TripResponse> getTrips(Pageable pageable) {
        Slice<Trip> trips = tripRepository.findAll(pageable);
        return trips.map(TripResponse::from);

    }

    @Override
    public Slice<TripResponse> getOwnTrips(String email, Pageable pageable) {
        User user = userRepository.findById(email).orElseThrow();
        Slice<Trip> trips = tripRepository.findAllByUser(user, pageable);
        return trips.map(TripResponse::from);
    }

    @Override
    public void registerTrip(CreateTripInput input) {
        User user = userRepository.findById(input.email()).orElseThrow();

        Trip trip = Trip.builder()
                .user(user)
                .tripName(input.tripName())
                .startDate(input.startDate())
                .endDate(input.endDate())
                .build();

        tripRepository.save(trip);
    }

    @Override
    public void modifyTrip(UpdateTripInput input) {
        User user = userRepository.findById(input.email()).orElseThrow();
        Trip trip = tripRepository.findById(input.tripId()).orElseThrow();

        if (trip.getUser().equals(user)) {

            input.startDate().ifPresent(trip::updateStartDate);
            input.endDate().ifPresent(trip::updateEndDate);

            input.cityIds().ifPresent(ids -> {
                List<City> cities = ids.stream()
                        .map(cityRepository::getReferenceById)
                        .toList();
                trip.updateCities(cities);
            });

            input.conceptIds().ifPresent(ids -> {
                List<Concept> concepts = ids.stream()
                        .map(conceptRepository::getReferenceById)
                        .toList();
                trip.updateConcepts(concepts);
            });
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public void removeTrip(String email, long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        if (trip.getUser().getEmail().equals(email)) {
            trip.delete();
        } else {
            throw new RuntimeException();
        }
    }

}
