package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByPostUseTrue(Pageable pageable);

    Optional<Post> findByIdAndPostUseTrue(Long id);
}
