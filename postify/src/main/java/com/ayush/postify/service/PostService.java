package com.ayush.postify.service;

import com.ayush.postify.domain.dtos.CreatePostRequest;
import com.ayush.postify.domain.dtos.UpdatePostRequest;
import com.ayush.postify.domain.entity.Category;
import com.ayush.postify.domain.entity.Post;
import com.ayush.postify.domain.entity.Tag;
import com.ayush.postify.domain.entity.User;
import com.ayush.postify.enums.PostStatus;
import com.ayush.postify.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(Long categoryId, Long tagId) {
        if (categoryId != null && tagId != null){
            Category categoryById = categoryService.getCategoryById(categoryId);
            Tag tagById = tagService.getTagById(tagId);

            return postRepository.findAllByPostStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    categoryById,
                    tagById
            );
        }
        if (categoryId != null){
            Category categoryById = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByPostStatusAndCategory(
                    PostStatus.PUBLISHED,
                    categoryById);
        }
        if (tagId != null){
            Tag tagById = tagService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tagById
            );
        }
        return postRepository.findAllByPostStatus(PostStatus.PUBLISHED);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with ID " + id));
    }

    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndPostStatus(user, PostStatus.DRAFT);
    }

    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setPostStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadTime(calculateReadingTime(createPostRequest.getContent()));

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        newPost.setCategory(category);

        Set<Long> tagIds = createPostRequest.getTagIds();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));

        return postRepository.save(newPost);
    }

    @Transactional
    public Post updatePost(Long id, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with id " + id));

        existingPost.setTitle(updatePostRequest.getTitle());
        String postContent = updatePostRequest.getContent();
        existingPost.setContent(postContent);
        existingPost.setPostStatus(updatePostRequest.getStatus());
        existingPost.setReadTime(calculateReadingTime(postContent));

        Long updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if(!existingPost.getCategory().getId().equals(updatePostRequestCategoryId)) {
            Category newCategory = categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(newCategory);
        }

        Set<Long> existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        Set<Long> updatePostRequestTagIds = updatePostRequest.getTagIds();
        if(!existingTagIds.equals(updatePostRequestTagIds)) {
            List<Tag> newTags = tagService.getTagByIds(updatePostRequestTagIds);
            existingPost.setTags(new HashSet<>(newTags));
        }

        return postRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }

    private Integer calculateReadingTime(String content) {
        if(content == null || content.isEmpty()) {
            return 0;
        }

        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }
}
