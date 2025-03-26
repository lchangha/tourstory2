package io.traveler.travel.trip.dto.response;

import java.math.BigDecimal;

import io.traveler.travel.trip.entity.PlanLocation;

public record LocationResponse(
        String placeName,
        String addressName,
        String categoryName,
        BigDecimal y,
        BigDecimal x,
        Integer orderNumber) {
            
    public static LocationResponse from(PlanLocation location) {
        return new LocationResponse(
                location.getPlaceName(),
                location.getAddressName(),
                location.getCategoryName(),
                location.getY(),
                location.getX(),
                location.getOrderNumber()
                );
    }
}
