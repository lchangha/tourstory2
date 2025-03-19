package io.traveler.travel.trip;

import io.traveler.travel.global.entity.TimeTrackedEntity;
import io.traveler.travel.trip.entity.Trip;
import io.traveler.travel.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trip_message")
@Getter
@NoArgsConstructor
public class Message extends TimeTrackedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;

}
