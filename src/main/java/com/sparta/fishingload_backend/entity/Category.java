package com.sparta.fishingload_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addPostList(Post post) {
        this.postList.add(post);
        post.setCategory(this);
    }
}
