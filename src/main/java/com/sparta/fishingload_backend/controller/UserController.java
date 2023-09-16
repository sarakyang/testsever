package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.MessageResponseDto;
import com.sparta.fishingload_backend.dto.SignupRequestDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.security.ValidationGroups;
import com.sparta.fishingload_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<MessageResponseDto> signup(@Validated(ValidationGroups.ValidationSequence.class) @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @DeleteMapping("/user/resign")
    public ResponseEntity<MessageResponseDto> resign(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.resign(userDetails.getUser());
    }

}