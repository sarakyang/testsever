package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.AccountResponseDto;
import com.sparta.fishingload_backend.dto.SignupRequestDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/myinfo")
    public AccountResponseDto MyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return accountService.MyInfo(userDetails);
    }

    @PutMapping("/account/{id}")
    public AccountResponseDto UserUpdate (@PathVariable Long id , @RequestBody SignupRequestDto signupRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.UserUpdate(id, signupRequestDto, userDetails);
    }

}
