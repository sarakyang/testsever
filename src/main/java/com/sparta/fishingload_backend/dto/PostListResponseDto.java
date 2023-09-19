package com.sparta.fishingload_backend.dto;

import com.sparta.fishingload_backend.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostListResponseDto {
    private final List<PostResponseDto> Posts = new ArrayList<>();

    public void setPost(PostResponseDto post) {
        Posts.add(post);
    }
}
