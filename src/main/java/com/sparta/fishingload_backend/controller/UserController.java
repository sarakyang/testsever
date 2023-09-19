package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.*;
import com.sparta.fishingload_backend.security.ValidationGroups;
import com.sparta.fishingload_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    // GET 요청 표준에 따라 GET 방식에 Body를 넣어 전송하는 방식은 피해야함
    // 더하여 민간함 전보의 경우 body에 담아 post 맵핑으로 가져오고 가져가는 것이 필요함
    //아이디 찾기
    @PostMapping("/user/findID")
    public FindUserResponseDto findUser (@RequestBody FindIdRequestDto findRequestDto) {
        return userService.findUser(findRequestDto);
    }

    //패스워드 찾기
    @PostMapping("/user/findPW")
    public ResponseEntity<MessageResponseDto> findPassword (@RequestBody FindRequestDto findRequestDto) {
        return userService.findPassword(findRequestDto);
    }

    @PostMapping("/user/userIdCheck")
    public ResponseEntity<MessageResponseDto> duplicate (@RequestBody FindUserRequestDto findRequestDto) {
        return userService.duplicate(findRequestDto);
    }

    @PutMapping("/user/change")
    public ResponseEntity<MessageResponseDto> changePassword(HttpServletRequest request
            , @RequestBody ChangPasswordRequestDto changPasswordRequestDto){
        return userService.changePassword(request ,changPasswordRequestDto);
    }
}