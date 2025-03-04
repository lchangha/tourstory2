package io.traveler.travel.user.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public User(String name, String nickname, String email, String password, LocalDate birth, String gender, String phone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.profileUrl == null) {
            this.profileUrl = "/profile.jpg";
        }
    }

    public void updateProFileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}