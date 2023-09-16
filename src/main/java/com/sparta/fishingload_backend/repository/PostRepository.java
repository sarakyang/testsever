package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
