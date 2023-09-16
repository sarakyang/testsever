package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.MessageResponseDto;
import com.sparta.fishingload_backend.dto.SignupRequestDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.security.ValidationGroups;
import com.sparta.fishingload_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "회원 가입 요청", description = "회원 정보가 생성됩니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/user/signup")
    public ResponseEntity<MessageResponseDto> signup(@Validated(ValidationGroups.ValidationSequence.class) @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @DeleteMapping("/user/resign")
    public ResponseEntity<MessageResponseDto> resign(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.resign(userDetails.getUser());
    }

}