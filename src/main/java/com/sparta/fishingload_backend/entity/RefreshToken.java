package com.sparta.fishingload_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "userid", nullable = false)
    private String userId;

    public RefreshToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public void updateToken(String token) {
        this.token = token;

    }
}