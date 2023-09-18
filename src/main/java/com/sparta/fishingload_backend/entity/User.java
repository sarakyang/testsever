package com.sparta.fishingload_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.fishingload_backend.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @JsonIgnore
    @Column(name = "account_use")
    private boolean accountUse = true;

    @JsonIgnore
    @Column(name = "admin", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Post> postList = new ArrayList<>();

    public User(String userId, String password, UserRoleEnum role, SignupRequestDto requestDto) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.nickname = requestDto.getNickname();
        this.email = requestDto.getEmail();
    }

    public void addPostList(Post post) {
        this.postList.add(post);
    }

    public void update(String password, SignupRequestDto signupRequestDto) {
        this.userId = signupRequestDto.getUserId();
        this.password = password;
        this.email = signupRequestDto.getEmail();
        this.nickname = signupRequestDto.getNickname();
    }
}