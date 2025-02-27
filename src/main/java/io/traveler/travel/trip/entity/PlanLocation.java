package io.traveler.travel.trip.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "plan_location")
@Getter
@NoArgsConstructor
public class PlanLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plans_id")
    private Long plansId;

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
    public PlanLocation(Long plansId, String placeName, String addressName, String categoryName, BigDecimal y, BigDecimal x) {
        this.plansId = plansId;
        this.placeName = placeName;
        this.addressName = addressName;
        this.categoryName = categoryName;
        this.y = y;
        this.x = x;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}