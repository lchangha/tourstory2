package io.traveler.travel.trip.service;


import io.traveler.travel.trip.dto.input.*;
import io.traveler.travel.trip.dto.request.*;
import io.traveler.travel.trip.dto.response.*;
import org.springframework.data.domain.*;

public interface TripService {
    TripResponse getOwnTrip(String username, long tripId);

    Slice<TripResponse> getOwnTrips(String username, Pageable pageable);

    void registerTrip(CreateTripInput createTripRequest);

    void modifyTrip(UpdateTripInput updateTripRequest);

    void removeTrip(String username, long tripId);

    TripResponse getTrip(long tripId);

    Slice<TripResponse> getTrips(Pageable pageable);
}
