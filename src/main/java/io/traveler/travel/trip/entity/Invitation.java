package io.traveler.travel.trip.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "invitations")
@Getter
@NoArgsConstructor
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trips_id")
    private Long tripsId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "invitation_date")
    private LocalDateTime invitationDate;

    @Column(name = "response_date")
    private LocalDateTime responseDate;

    @Column(name = "status")
    private String status;

    @Builder
    public Invitation(Long tripsId, Long userId, LocalDateTime responseDate) {
        this.tripsId = tripsId;
        this.userId = userId;
        this.responseDate = responseDate;
    }

    @PrePersist
    private void onCreate() {
        this.invitationDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "invited";
        }
    }

    public void accept() {
        this.status = "accepted";
    }

    public void reject() {
        this.status = "rejected";
    }
}