package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndCommentUseTrue(Long id);
}
