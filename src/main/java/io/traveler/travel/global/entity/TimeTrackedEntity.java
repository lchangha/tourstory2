package io.traveler.travel.global.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class TimeTrackedEntity {

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (!isDeleted) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void delete() {
        if (!isDeleted) {
            this.isDeleted = true;
            this.deletedAt = LocalDateTime.now();
        } else {
            throw new RuntimeException("Cannot delete this entity after deletion");
        }
    }
}
