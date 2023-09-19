package com.sparta.fishingload_backend.dto;

import lombok.Getter;

@Getter
public class FindRequestDto {
    private String email;
    private String userId;

    public FindRequestDto(String userId, String email) {
        this.email = email;
        this.userId = userId;
    }
}
