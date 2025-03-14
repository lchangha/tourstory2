package io.traveler.travel.trip.entity;

import io.traveler.travel.user.entity.User;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User tripOwner;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "trip_name")
    private String tripName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Trip(User tripOwner, LocalDate startDate, LocalDate endDate, String tripName) {
        this.tripOwner = tripOwner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripName = tripName;
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}