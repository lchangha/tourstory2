package io.traveler.travel.trip.dto.request;

import java.time.*;
import java.util.*;

public record UpdateTripRequest(
        Long tripId,
        Optional<String>tripName,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate,
        Optional<int[]> cityIds,
        Optional<int[]> conceptIds
) {
}
