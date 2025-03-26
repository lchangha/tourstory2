package io.traveler.travel.trip.dto.response;

import java.time.LocalDate;
import java.util.List;

import io.traveler.travel.trip.entity.*;

public record TripResponse(
    LocalDate startDate,
    LocalDate endDate,
    List<String> citiesName,
    List<String> conceptsTitle,
    List<LocationResponse> Location
    )
{

    public static TripResponse from(Trip trip) {
        return new TripResponse(
            trip.getStartDate(),
            trip.getEndDate(),
            trip.getCities().stream().map(city -> city.getName()).toList(),
            trip.getConcepts().stream().map(concept -> concept.getTitle()).toList(),
            trip.getLocations().stream().map(LocationResponse::from).toList()
        );
    }
}
