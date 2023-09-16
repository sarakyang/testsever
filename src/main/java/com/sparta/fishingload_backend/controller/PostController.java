package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.PostRequestDto;
import com.sparta.fishingload_backend.dto.PostResponseDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, userDetails.getUser());
    }

    @GetMapping("/post")
    public Page<PostResponseDto> getPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc) {
        return postService.getPosts(page-1, size, sortBy, isAsc);
    }
}
