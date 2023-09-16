package com.sparta.fishingload_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindUserResponseDto {
    private String userId;

    public FindUserResponseDto(String userId) {
        this.userId = userId;
    }
}
