package io.traveler.travel.trip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.traveler.travel.trip.dto.response.LocationResponse;
import io.traveler.travel.trip.service.PlanLocationService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/trip/private/{tripId}/location")
public class PlanLocationController {
    private final PlanLocationService planLocationService;

    public PlanLocationController(PlanLocationService planLocationService) {
        this.planLocationService = planLocationService;
    }

    @GetMapping()
    public List<LocationResponse> getPlanLocations(@PathVariable long tripId) {
        return planLocationService.getPlanLocations(tripId);
    }

    @PostMapping()
    public void createPlanLocation(@RequestBody CreatePlanLocationRequest createPlanLocationRequest) {
        planLocationService.createPlanLocation(createPlanLocationRequest);
    }

    @GetMapping("{locationId}")
    public LocationResponse getPlanLocation(@PathVariable long locationId) {
        return new String();
    }



    



}
