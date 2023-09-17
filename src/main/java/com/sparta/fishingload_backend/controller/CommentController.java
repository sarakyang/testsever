package com.sparta.fishingload_backend.controller;

import com.sparta.fishingload_backend.dto.CommentRequestDto;
import com.sparta.fishingload_backend.dto.CommentResponseDto;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import com.sparta.fishingload_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id, requestDto, userDetails.getUser());
    }
}
