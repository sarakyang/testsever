package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.AccountResponseDto;
import com.sparta.fishingload_backend.dto.MyPageResonseDto;
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

    //개인정보
    @GetMapping("/account/myinfo")
    public AccountResponseDto MyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return accountService.MyInfo(userDetails);
    }
    //개인정보 수정
    @PutMapping("/account/{id}")
    public AccountResponseDto UserUpdate (@PathVariable Long id , @RequestBody SignupRequestDto signupRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.UserUpdate(id, signupRequestDto, userDetails);
    }

    //마이페이지 조회
    @GetMapping("/account/mypage")
    public MyPageResonseDto mypage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.mypage(userDetails);
    }
}
