package com.sparta.fishingload_backend.controller;


import com.sparta.fishingload_backend.dto.SignupRequestDto;
import com.sparta.fishingload_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto,
                                         BindingResult bindingResult) {
        //예외처리
        List<FieldError>fieldErrors = bindingResult.getFieldErrors();
        // 오류 발생의 여부 (종류별로 달아줌 )
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : ", fieldError.getDefaultMessage());
                return new ResponseEntity<>("상태코드 : " + HttpStatus.BAD_REQUEST.value() +
                        " 메세지 " + fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return userService.signup(requestDto);
    }

}
