package io.traveler.travel.global.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "concept")
@Getter
@NoArgsConstructor
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Builder
    public Concept(String title, String description) {
        this.title = title;
        this.description = description;
    }
}