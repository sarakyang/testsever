package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.*;
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
    public ResponseEntity<MessageResponseDto> signup(@Validated(ValidationGroups.ValidationSequence.class)
                                                         @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @GetMapping("/user/findID")
    public FindUserResponseDto findUser (@RequestBody FindRequestDto findRequestDto) {
        return userService.findUser(findRequestDto);
    }

    @GetMapping("/user/findPW")
    public FindPasswordResponseDto findPassword (@RequestBody FindRequestDto findRequestDto) {
        return userService.findPassword(findRequestDto);
    }

    @PostMapping("/user/userIdCheck")
    public ResponseEntity<MessageResponseDto> duplicate (@RequestBody FindRequestDto findRequestDto) {
        return userService.duplicate(findRequestDto);
    }
}