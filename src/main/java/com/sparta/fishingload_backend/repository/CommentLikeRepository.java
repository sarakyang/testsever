package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByUser_IdAndComment_Id(Long userId, Long commentId);
}
