package io.traveler.travel.trip.service.impl;

import io.traveler.travel.trip.dto.input.*;
import io.traveler.travel.trip.dto.response.*;
import io.traveler.travel.trip.entity.*;
import io.traveler.travel.trip.repository.*;
import io.traveler.travel.trip.service.*;
import io.traveler.travel.user.entity.*;
import io.traveler.travel.user.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
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
    public void registerTrip(CreateTripInput createTripRequest) {

        Trip.builder()
                .user()
    }

    @Override
    public void modifyTrip(UpdateTripInput updateTripRequest) {

    }

    @Override
    public void removeTrip(String email, long tripId) {

    }


}
