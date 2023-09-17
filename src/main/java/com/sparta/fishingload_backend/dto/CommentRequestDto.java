package com.sparta.fishingload_backend.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long postId;
    private String comment;
}
