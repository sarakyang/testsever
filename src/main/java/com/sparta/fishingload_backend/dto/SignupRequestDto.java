package com.sparta.fishingload_backend.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private String email;

    //어드민
    private boolean admin = false;
    private String adminToken = "";
}
