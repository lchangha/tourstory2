package io.traveler.travel.trip.controller;

import io.traveler.travel.trip.dto.request.*;
import io.traveler.travel.trip.dto.response.*;
import io.traveler.travel.trip.service.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trip/private")
public class PrivateTripController {
    private final TripService tripService;

    public PrivateTripController(TripService tripService) {
        this.tripService = tripService;
    }



    @GetMapping("{tripId}")
    public TripResponse getPrivateTrip(@AuthenticationPrincipal UserDetails user,
                                       @PathVariable int tripId) {
        return tripService.getOwnTrip(user.getUsername(), tripId);
    }

    @GetMapping()
    public Slice<TripResponse> getPrivateTrips(@AuthenticationPrincipal UserDetails user,
                                               @RequestParam(defaultValue = "0") @Min(0) int page,
                                               @RequestParam(defaultValue = "10") @Min(1) @Max(20) int size) {

        return tripService.getOwnTrips(user.getUsername(), PageRequest.of(page, size));
    }

    @PostMapping()
    public void createTrip(@AuthenticationPrincipal UserDetails user,
                           @RequestBody @Valid CreateTripRequest createTripRequest) {
        tripService.registerTrip(createTripRequest);
    }

    @PutMapping("{tripId}")
    public void updateTrip(@AuthenticationPrincipal UserDetails user,
                           @RequestBody UpdateTripRequest updateTripRequest) {
        tripService.modifyTrip(updateTripRequest);
    }

    @DeleteMapping("{tripId}")
    public void updateTrip(@AuthenticationPrincipal UserDetails user,
                           @PathVariable int tripId) {
        tripService.removeTrip(user.getUsername(), tripId);
    }

}
