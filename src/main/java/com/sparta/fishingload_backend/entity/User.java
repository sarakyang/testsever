package com.sparta.fishingload_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false, unique = true)
    private String userid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "admin" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(name = "account_Use")
    private boolean accountUse= true ;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> postList = new ArrayList<>();

    public User(String userid, String password, UserRoleEnum role, String email, String nickname) {
        this.userid = userid;
        this.password = password;
        this.role = role;
        this.email = email;
        this.nickname = nickname;
    }

    public void addPostList(Post post) {
        this.postList.add(post);
    }
}
