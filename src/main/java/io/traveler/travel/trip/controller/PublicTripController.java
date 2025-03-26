package io.traveler.travel.trip.controller;

import io.traveler.travel.trip.dto.response.*;
import io.traveler.travel.trip.service.*;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicTripController {
    private final TripService tripService;

    public PublicTripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("{tripId}")
    public TripResponse getTrip(@PathVariable int tripId) {
        return tripService.getTrip(tripId);
    }

    @GetMapping()
    public Slice<TripResponse> getTrips(@RequestParam(defaultValue = "0") @Min(0) int page,
                                        @RequestParam(defaultValue = "10") @Min(1) @Max(20) int size) {
        return tripService.getTrips(PageRequest.of(page, size));
    }
}
