package com.sparta.fishingload_backend.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public class FindPasswordResponseDto {
    private String password;

    public FindPasswordResponseDto(String password) {
        this.password = password;
    }
}
