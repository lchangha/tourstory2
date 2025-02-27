package io.traveler.travel.trip.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "trips")
@Getter
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "trip_name")
    private String tripName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Trip(Long userId, LocalDate startDate, LocalDate endDate, String tripName) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripName = tripName;
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}