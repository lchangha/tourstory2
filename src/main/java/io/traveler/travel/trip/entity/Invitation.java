package io.traveler.travel.trip.entity;

import io.traveler.travel.user.entity.User;
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

    @JoinColumn(name = "trips_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User guest;

    @Column(name = "invitation_date")
    private LocalDateTime invitationDate;

    @Column(name = "response_date")
    private LocalDateTime responseDate;

    @Column(name = "status")
    private String status;

    @Builder
    public Invitation(Trip trip, User guest, LocalDateTime responseDate) {
        this.trip = trip;
        this.guest = guest;
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