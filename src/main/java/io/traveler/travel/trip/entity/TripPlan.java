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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "trips_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Builder
    public TripPlan(Trip trip, LocalDate scheduleDate) {
        this.trip = trip;
        this.scheduleDate = scheduleDate;
    }

}