package com.sparta.fishingload_backend.dto;

import com.sparta.fishingload_backend.security.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @Size(min = 4, max = 15 , groups = ValidationGroups.SizeCheckGroup.class)
    @Pattern(message = "잘못된 userId입니다." , regexp = "^[A-Za-z0-9]+$", groups = ValidationGroups.PatternCheckGroup.class)
    private String userId;

    @Size(min = 8, max = 20, groups = ValidationGroups.SizeCheckGroup.class)// 특수문자 , 대문자, 소문자 1개씩 포함 // 이건
    @Pattern(message = "잘못된 password입니다." , regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$", groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    @Size(min = 2 , max = 10 , groups = ValidationGroups.SizeCheckGroup.class)
    @Pattern(message = "잘못된 nickname입니다. " , regexp = "^[a-z0-9A-Z가-힝]+$", groups = ValidationGroups.PatternCheckGroup.class)
    private String nickname;

    @Pattern(message = "잘못된 email입니다. " , regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$" ,
            groups = ValidationGroups.PatternCheckGroup.class)
    private String email;

}
