package com.sparta.fishingload_backend.dto;

import com.sparta.fishingload_backend.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResonseDto {
    private User user;

    public MyPageResonseDto(User user) {
        this.user = user;
    }
}
