package com.sparta.fishingload_backend.service;

import com.sparta.fishingload_backend.dto.PostRequestDto;
import com.sparta.fishingload_backend.dto.PostResponseDto;
import com.sparta.fishingload_backend.entity.Category;
import com.sparta.fishingload_backend.entity.Post;
import com.sparta.fishingload_backend.entity.User;
import com.sparta.fishingload_backend.repository.CategoryRepository;
import com.sparta.fishingload_backend.repository.PostRepository;
import com.sparta.fishingload_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto);
        post.setNickname(user.getNickname());

        Category category = findCategory(requestDto.getCategoryId());
        category.addPostList(post);

        User userSelect = findUser(user.getUserid());
        userSelect.addPostList(post);
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    private User findUser(String userId) {
        return userRepository.findByUseridAndUserUseTrue(userId).orElseThrow(() ->
                new NullPointerException("해당 유저는 존재하지 않습니다.")
        );
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 카테고리는 존재하지 않습니다.")
        );
    }
}
