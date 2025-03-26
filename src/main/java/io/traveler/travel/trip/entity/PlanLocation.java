package io.traveler.travel.trip.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;
import java.time.*;

@Entity
@Table(name = "plan_location")
@Getter
@NoArgsConstructor
public class PlanLocation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "trips_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "y")
    private BigDecimal y;

    @Column(name = "x")
    private BigDecimal x;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public PlanLocation(Trip trip, String placeName, String addressName, String categoryName, BigDecimal y, BigDecimal x) {
        this.trip = trip;
        this.placeName = placeName;
        this.addressName = addressName;
        this.categoryName = categoryName;
        this.y = y;
        this.x = x;
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}