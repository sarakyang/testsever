package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.Category;
import com.sparta.fishingload_backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByPostUseTrue(Pageable pageable);

    Optional<Post> findByIdAndPostUseTrue(Long id);

    Page<Post> findAllByPostUseTrueAndAccountId(Pageable pageable, String id);

    List<Post> findAllByCategoryAndPostUseTrue(Category category);
}
