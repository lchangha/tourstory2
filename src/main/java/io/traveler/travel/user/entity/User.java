package io.traveler.travel.user.entity;

import io.traveler.travel.common.entity.TimeTrackedEntity;
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
public class User extends TimeTrackedEntity {

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


    @Override
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.profileUrl == null) {
            this.profileUrl = "/profile.jpg";
        }
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void updateGender(String gender) {
        this.gender = gender;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateProFileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}