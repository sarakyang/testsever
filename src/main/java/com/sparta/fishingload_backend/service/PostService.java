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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getPosts(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<PostResponseDto> pageList = postRepository.findAllByPostUseTrue(pageable).map(PostResponseDto::new);

        return pageList;
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        PostResponseDto responseDto = new PostResponseDto(post);

        return responseDto;
    }

    private Post findPost(Long id) {
        return postRepository.findByIdAndPostUseTrue(id).orElseThrow(() ->
                new NullPointerException("선택한 게시물은 존재하지 않습니다.")
        );
    }

    private User findUser(String userId) {
        return userRepository.findByUseridAndAccountUseTrue(userId).orElseThrow(() ->
                new NullPointerException("해당 유저는 존재하지 않습니다.")
        );
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 카테고리는 존재하지 않습니다.")
        );
    }

}
