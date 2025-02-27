package io.traveler.travel.trip.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "trip_plans")
@Getter
@NoArgsConstructor
public class TripPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trips_id")
    private Long tripsId;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Builder
    public TripPlan(Long tripsId, LocalDate scheduleDate) {
        this.tripsId = tripsId;
        this.scheduleDate = scheduleDate;
    }

}