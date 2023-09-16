package com.sparta.fishingload_backend.entity;

import com.sparta.fishingload_backend.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false, unique = true)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "account_use")
    private boolean accountUse = true;

    @Column(name = "admin", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String userId, String password, UserRoleEnum role, SignupRequestDto requestDto) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.nickname = requestDto.getNickname();
        this.email = requestDto.getEmail();
    }
}