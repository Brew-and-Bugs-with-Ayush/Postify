package com.ayush.postify.repository;

import com.ayush.postify.domain.entity.Category;
import com.ayush.postify.domain.entity.Post;
import com.ayush.postify.domain.entity.Tag;
import com.ayush.postify.domain.entity.User;
import com.ayush.postify.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post , Long> {
    List<Post> findAllByPostStatusAndCategoryAndTagsContaining(PostStatus postStatus,
                                                           Category category,
                                                           Tag tag);

    List<Post> findAllByPostStatusAndCategory(PostStatus postStatus, Category category);

    List<Post> findAllByPostStatusAndTagsContaining(PostStatus postStatus, Tag tagById);

    List<Post> findAllByPostStatus(PostStatus postStatus);

    List<Post> findAllByAuthorAndPostStatus(User author, PostStatus status);
}
