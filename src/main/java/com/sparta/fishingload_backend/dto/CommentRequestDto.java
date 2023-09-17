package com.sparta.fishingload_backend.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long boardId;
    private String comment;
}
