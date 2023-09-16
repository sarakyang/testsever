package com.sparta.fishingload_backend.repository;

import com.sparta.fishingload_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
