package com.sparta.fishingload_backend.dto;

import com.sparta.fishingload_backend.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private String contents;
    private int postLike;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String fishtype;
    private String locationdate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getNickname();
        this.contents = post.getContents();
        this.postLike = post.getPostLike();
        this.createdTime = post.getCreatedTime();
        this.modifiedTime = post.getModifiedTime();
    }
}
