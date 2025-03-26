package io.traveler.travel.trip.entity;

import io.traveler.travel.global.entity.*;
import io.traveler.travel.user.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

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

    @Builder
    public Trip(User user, LocalDate startDate, LocalDate endDate, String tripName) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripName = tripName;
    }

}