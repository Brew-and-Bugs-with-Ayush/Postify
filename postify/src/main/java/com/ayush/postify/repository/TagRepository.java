package com.ayush.postify.repository;

import com.ayush.postify.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag , Long> {

    @Query("select t from Tag t left join fetch t.posts")
    List<Tag> findAllByWithPostCount();

    List<Tag> findByNameIn(Set<String> names);
}
