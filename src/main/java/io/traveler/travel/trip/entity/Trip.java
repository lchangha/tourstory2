package io.traveler.travel.trip.entity;

import io.traveler.travel.global.entity.*;
import io.traveler.travel.user.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@NoArgsConstructor
public class Trip  extends TimeTrackedEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "trip_name")
    private String tripName;

    @JoinTable(name = "trips_city",
               joinColumns = @JoinColumn(name = "city_id"), 
               inverseJoinColumns = @JoinColumn(name ="trips_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<City> cities;

    @JoinTable(name = "trips_city",
               joinColumns = @JoinColumn(name = "concept_id"), 
               inverseJoinColumns = @JoinColumn(name ="trips_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Concept> concepts;

    @JoinColumn(name = "trips_id")
    @OneToMany
    private List<PlanLocation> locations;

    @Builder
    public Trip(User user,
                LocalDate startDate,
                LocalDate endDate,
                String tripName,
                List<City> cities,
                List<Concept> concepts) {

        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripName = tripName;
        this.cities = cities;
        this.concepts = concepts;
    }

    public void updateStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void updateEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void updateTripName(String tripName) {
        this.tripName = tripName;
    }

    public void updateCities(List<City> cities) {
        this.cities = cities;
    }

    public void updateConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public void updateLocations(List<PlanLocation> locations) {
        this.locations = locations;
    }

    public void addLocation(PlanLocation location) {
        locations.add(location);
    }

    public void removeLocation(PlanLocation location) {
        locations.remove(location);
    }

    


    

}