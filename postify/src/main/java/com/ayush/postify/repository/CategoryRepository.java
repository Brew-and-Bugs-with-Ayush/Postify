package com.ayush.postify.repository;

import com.ayush.postify.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    @Query("SELECT c FROM Category c LEFT JOIN fetch c.posts")
    List<Category> findAllWithPostCount();

    boolean existsByNameIgnoreCase(String name);
}
