package com.sparta.fishingload_backend.service;

import com.sparta.fishingload_backend.dto.CommentRequestDto;
import com.sparta.fishingload_backend.dto.CommentResponseDto;
import com.sparta.fishingload_backend.dto.MessageResponseDto;
import com.sparta.fishingload_backend.entity.Comment;
import com.sparta.fishingload_backend.entity.Post;
import com.sparta.fishingload_backend.entity.User;
import com.sparta.fishingload_backend.entity.UserRoleEnum;
import com.sparta.fishingload_backend.repository.CommentRepository;
import com.sparta.fishingload_backend.repository.PostRepository;
import com.sparta.fishingload_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {
        Post post = findPost(id);

        String userid = user.getUserId();
        User user_selcet = findUser(userid);

        Comment comment = new Comment(requestDto);
        comment.setAccountId(userid);

        post.addCommentList(comment);
        user_selcet.addCommentList(comment);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(id);

        if (!user.getUserId().equals(comment.getAccountId()) && user.getRole() != UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("해당 댓글의 작성자만 수정할 수 있습니다.");
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<MessageResponseDto> deleteComment(Long id, User user) {
        Comment comment = findComment(id);

        if (!user.getUserId().equals(comment.getAccountId()) && user.getRole() != UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("해당 댓글의 작성자만 삭제할 수 있습니다.");
        }

        comment.setCommentUse(false);

        MessageResponseDto message = new MessageResponseDto("게시물 삭제를 성공했습니다.", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    private Comment findComment(Long id) {
        return commentRepository.findByIdAndCommentUseTrue(id).orElseThrow(() ->
                new NullPointerException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    private Post findPost(Long id) {
        return postRepository.findByIdAndPostUseTrue(id).orElseThrow(() ->
                new NullPointerException("선택한 게시물은 존재하지 않습니다.")
        );
    }

    private User findUser(String userId) {
        return userRepository.findByUserIdAndAccountUseTrue(userId).orElseThrow(() ->
                new NullPointerException("해당 유저는 존재하지 않습니다.")
        );
    }
}
