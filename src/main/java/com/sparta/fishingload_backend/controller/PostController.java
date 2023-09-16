package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.PostRequestDto;
import com.sparta.fishingload_backend.dto.PostResponseDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/board")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, userDetails.getUser());
    }
}
