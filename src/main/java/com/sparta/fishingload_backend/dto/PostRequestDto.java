package com.sparta.fishingload_backend.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private String fishtype;
    private String locationdate;
    private Double coordinates;
    private Long categoryId;
}
