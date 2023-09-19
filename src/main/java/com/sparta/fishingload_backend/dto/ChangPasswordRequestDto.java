package com.sparta.fishingload_backend.dto;

import com.sparta.fishingload_backend.security.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ChangPasswordRequestDto {
    @NotBlank
    @Size(min = 8, max = 20, groups = ValidationGroups.SizeCheckGroup.class)// 특수문자 , 대문자, 소문자 1개씩 포함 // 이건
    @Pattern(message = "잘못된 password입니다." , regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$", groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

}
